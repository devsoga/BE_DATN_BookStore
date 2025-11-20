package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.PaymentDTO;
import com.devsoga.BookStore_V2.services.PaymentService;
import com.devsoga.BookStore_V2.services.VnPayOrderCache;
import com.devsoga.BookStore_V2.services.OrderService;
import com.devsoga.BookStore_V2.services.TransferHistoryService;
import com.devsoga.BookStore_V2.enties.InvoiceEntity;
import com.devsoga.BookStore_V2.enties.TransferHistoryEntity;
import com.devsoga.BookStore_V2.repositories.InvoiceRepository;
import com.devsoga.BookStore_V2.repositories.TransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/vnpay")
public class VNPAYController {

    private final PaymentService paymentService;
    private final VnPayOrderCache vnPayOrderCache;
    private final OrderService orderService;
    private final TransferHistoryService transferHistoryService;
    private final InvoiceRepository invoiceRepository;
    private final TransferHistoryRepository transferHistoryRepository;
    @Value("${payment.vnPay.frontendUrl:}")
    private String vnPayFrontendUrl;
    @Value("${payment.vnPay.frontendFailUrl:}")
    private String vnPayFrontendFailUrl;

    @Autowired
    public VNPAYController(PaymentService paymentService, VnPayOrderCache vnPayOrderCache, 
                          OrderService orderService, TransferHistoryService transferHistoryService,
                          InvoiceRepository invoiceRepository, TransferHistoryRepository transferHistoryRepository) {
        this.paymentService = paymentService;
        this.vnPayOrderCache = vnPayOrderCache;
        this.orderService = orderService;
        this.transferHistoryService = transferHistoryService;
        this.invoiceRepository = invoiceRepository;
        this.transferHistoryRepository = transferHistoryRepository;
    }

    @GetMapping("/create_payment")
    public ResponseEntity<PaymentDTO.VNPayResponse> pay(HttpServletRequest request) {
        PaymentDTO.VNPayResponse resp = paymentService.createVnPayPayment(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/vn-pay-callback")
    public ResponseEntity<?> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        String txnRef = request.getParameter("vnp_TxnRef");
        String transactionNo = request.getParameter("vnp_TransactionNo");
        String vnpAmountStr = request.getParameter("vnp_Amount");
        String bankCode = request.getParameter("vnp_BankCode");
        String payDate = request.getParameter("vnp_PayDate");

        String orderCode = null;
        if (txnRef != null) orderCode = vnPayOrderCache.get(txnRef);
        
        // If no orderCode from cache, use txnRef as fallback
        if (orderCode == null) orderCode = txnRef;

        // parse amount if present (VNPay sends amount in smallest currency unit, typically multiplied by 100)
        Long amountRaw = null;
        BigDecimal amount = null;
        if (vnpAmountStr != null) {
            try {
                amountRaw = Long.parseLong(vnpAmountStr);
                // normalize by dividing by 100 to get the original amount unit
                amount = BigDecimal.valueOf(amountRaw).divide(BigDecimal.valueOf(100));
            } catch (NumberFormatException ignore) {
                amountRaw = null;
                amount = null;
            }
        }

        if ("00".equals(status)) {
            // Validate orderCode exists and amount matches
            try {
                if (orderCode == null || orderCode.isBlank()) {
                    var body = Map.of(
                        "code", "01", "message", "Missing orderCode",
                        "txnRef", txnRef, "transactionNo", transactionNo
                    );
                    return buildFailureResponse(request, HttpStatus.BAD_REQUEST, orderCode, txnRef, amountRaw, amount, body);
                }

                var orderOpt = invoiceRepository.findByOrderCode(orderCode);
                if (orderOpt.isEmpty()) {
                    var body = Map.of(
                        "code", "02", "message", "Order not found: " + orderCode,
                        "orderCode", orderCode, "txnRef", txnRef, "transactionNo", transactionNo
                    );
                    return buildFailureResponse(request, HttpStatus.NOT_FOUND, orderCode, txnRef, amountRaw, amount, body);
                }

                InvoiceEntity order = orderOpt.get();
                
                // Validate amount matches order final_amount
                if (amount == null || order.getFinalAmount() == null || amount.compareTo(order.getFinalAmount()) != 0) {
                    var body = Map.of(
                        "code", "03", "message", "Amount mismatch. Expected: " + order.getFinalAmount() + ", Received: " + amount,
                        "orderCode", orderCode, "txnRef", txnRef, "transactionNo", transactionNo,
                        "amountRaw", amountRaw, "amount", amount == null ? null : amount.toString()
                    );
                    return buildFailureResponse(request, HttpStatus.BAD_REQUEST, orderCode, txnRef, amountRaw, amount, body);
                }

                // Create transfer_history record
                TransferHistoryEntity transferHistory = new TransferHistoryEntity();
                transferHistory.setOrderCode(orderCode);
                transferHistory.setPaymentMethod(TransferHistoryEntity.PaymentMethod.VNPAY);
                transferHistory.setTransferAmount(amount);
                
                // Create comprehensive description for VNPay payment
                StringBuilder desc = new StringBuilder();
                desc.append("VNPay Payment");
                if (txnRef != null) desc.append(" - TxnRef: ").append(txnRef);
                if (transactionNo != null) desc.append(" - TransactionNo: ").append(transactionNo);
                if (bankCode != null) desc.append(" - Bank: ").append(bankCode);
                if (payDate != null) desc.append(" - PayDate: ").append(payDate);
                
                transferHistory.setDescription(desc.toString());
                
                transferHistoryRepository.save(transferHistory);

                // Update order is_paid = true (only isPaid, do not change order_status)
                var updateResult = orderService.updateIsPaidOnly(orderCode, true);
                if (updateResult.getStatusCode() != 200) {
                    var body = Map.of(
                        "code", "04", "message", "Failed to update order payment status: " + updateResult.getMessage(),
                        "orderCode", orderCode, "txnRef", txnRef, "transactionNo", transactionNo
                    );
                    return buildFailureResponse(request, HttpStatus.INTERNAL_SERVER_ERROR, orderCode, txnRef, amountRaw, amount, body);
                }

                // Prepare response map
                Map<String, Object> respMap = Map.of(
                    "code", "00",
                    "message", "Success",
                    "orderCode", orderCode,
                    "txnRef", txnRef,
                    "transactionNo", transactionNo,
                    "amountRaw", amountRaw,
                    "amount", amount.toString()
                );

                // If a frontend URL is configured and the caller likely expects HTML, redirect the browser
                String accept = request.getHeader("Accept");
                if (vnPayFrontendUrl != null && !vnPayFrontendUrl.isBlank() && (accept == null || !accept.contains("application/json"))) {
                    // Build redirect URL with basic params
                    String redirectUrl = vnPayFrontendUrl + (vnPayFrontendUrl.contains("?") ? "&" : "?")
                        + "orderCode=" + java.net.URLEncoder.encode(orderCode == null ? "" : orderCode, java.nio.charset.StandardCharsets.UTF_8)
                        + "&status=success"
                        + "&amount=" + (amount == null ? "" : java.net.URLEncoder.encode(amount.toString(), java.nio.charset.StandardCharsets.UTF_8));
                    return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
                }

                return ResponseEntity.ok(respMap);
                
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "code", "05", "message", "Internal error: " + e.getMessage(),
                    "orderCode", orderCode, "txnRef", txnRef, "transactionNo", transactionNo
                ));
            }
        } else {
            var body = Map.of(
                    "code", status == null ? "01" : status,
                    "message", "Failed",
                    "orderCode", orderCode == null ? txnRef : orderCode,
                    "txnRef", txnRef,
                    "amountRaw", amountRaw,
                    "amount", amount == null ? null : amount.toString()
            );
            return buildFailureResponse(request, HttpStatus.BAD_REQUEST, orderCode == null ? txnRef : orderCode, txnRef, amountRaw, amount, body);
        }
    }

    private ResponseEntity<?> buildFailureResponse(HttpServletRequest request, HttpStatus statusCode, String orderCode, String txnRef, Long amountRaw, BigDecimal amount, Map<String, ?> body) {
        String accept = request.getHeader("Accept");
        if (vnPayFrontendFailUrl != null && !vnPayFrontendFailUrl.isBlank() && (accept == null || !accept.contains("application/json"))) {
            try {
                String redirectUrl = vnPayFrontendFailUrl + (vnPayFrontendFailUrl.contains("?") ? "&" : "?")
                        + "orderCode=" + java.net.URLEncoder.encode(orderCode == null ? "" : orderCode, java.nio.charset.StandardCharsets.UTF_8)
                        + "&status=failure"
                        + "&amount=" + (amount == null ? "" : java.net.URLEncoder.encode(amount.toString(), java.nio.charset.StandardCharsets.UTF_8));
                // Optionally include message if present
                Object msg = body == null ? null : body.get("message");
                if (msg != null) {
                    redirectUrl += "&message=" + java.net.URLEncoder.encode(String.valueOf(msg), java.nio.charset.StandardCharsets.UTF_8);
                }
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
            } catch (Exception ignored) {
                // Fall through to return JSON body on error building redirect
            }
        }
        return ResponseEntity.status(statusCode).body(body);
    }
}
