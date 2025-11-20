package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.SepayTransferRequest;
import com.devsoga.BookStore_V2.enties.InvoiceEntity;
import com.devsoga.BookStore_V2.enties.TransferHistoryEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.InvoiceRepository;
import com.devsoga.BookStore_V2.repositories.TransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TransferHistoryService {

    @Autowired
    private TransferHistoryRepository transferHistoryRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // Match order codes like ORD12345, ORD-12345, ORD_1763669579008 (case-insensitive)
    private static final Pattern ORDER_CODE_PATTERN = Pattern.compile("\\bORD[-_ ]?\\d+\\b", Pattern.CASE_INSENSITIVE);

    @Transactional
    public BaseRespone processSepayTransfer(SepayTransferRequest request) {
        BaseRespone response = new BaseRespone();
        
        try {
            // Extract order code (from possible fields) but require it to appear in `content`
            String orderCode = extractOrderCode(request);
            if (orderCode == null) {
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Order code not found in transfer content");
                response.setData(null);
                return response;
            }

            boolean contentContainsOrder = false;
            if (request.getContent() != null) {
                Matcher mContent = ORDER_CODE_PATTERN.matcher(request.getContent());
                if (mContent.find()) {
                    String found = mContent.group();
                    contentContainsOrder = found.equalsIgnoreCase(orderCode);
                }
            }

            if (!contentContainsOrder) {
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Order code not found in transfer content");
                response.setData(null);
                return response;
            }

            // Find the order
            Optional<InvoiceEntity> orderOpt = invoiceRepository.findByOrderCode(orderCode);
            if (orderOpt.isEmpty()) {
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("Order not found: " + orderCode);
                response.setData(null);
                return response;
            }

            InvoiceEntity order = orderOpt.get();

            // Skip processing if order payment method is Cash
            if (order.getPaymentMethod() == InvoiceEntity.PaymentMethod.Cash) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Transfer notification ignored for cash payment order");
                response.setData(null);
                return response;
            }

            // Check if transfer amount matches order final amount
            if (request.getTransferAmount() == null || order.getFinalAmount() == null) {
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Transfer amount or order final amount is null");
                response.setData(null);
                return response;
            }

            BigDecimal transferAmount = request.getTransferAmount();
            BigDecimal finalAmount = order.getFinalAmount();

            // Only when BOTH conditions are satisfied:
            // 1) transferAmount == finalAmount
            // 2) the original `content` contains the order code (already validated above)
            boolean amountMatches = transferAmount.compareTo(finalAmount) == 0;

            if (!amountMatches || !contentContainsOrder) {
                // Do NOT create transfer_history unless both conditions are met
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Conditions not met: transfer not recorded. Amount match: " + amountMatches + ", content contains order: " + contentContainsOrder);
                response.setData(null);
                return response;
            }

            // Both conditions met -> save transfer history and mark order as paid
            saveTransferHistory(request, orderCode, true);

            order.setIsPaid(true);
            invoiceRepository.save(order);

            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Transfer processed successfully and order marked as paid");
            response.setData(orderCode);

        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error processing transfer: " + e.getMessage());
            response.setData(null);
        }

        return response;
    }

    public BaseRespone getByOrderCode(String orderCode) {
        BaseRespone response = new BaseRespone();
        try {
            java.util.List<TransferHistoryEntity> list = transferHistoryRepository.findByOrderCode(orderCode);
            response.setStatusCode(org.springframework.http.HttpStatus.OK.value());
            response.setMessage("OK");
            response.setData(list);
        } catch (Exception e) {
            response.setStatusCode(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error retrieving transfer history: " + e.getMessage());
            response.setData(null);
        }
        return response;
    }

    private String extractOrderCode(SepayTransferRequest request) {
        if (request == null) return null;

        // Try several candidate fields in order
        String[] candidates = new String[]{request.getContent(), request.getReferenceCode(), request.getCode(), request.getDescription()};
        for (String candidate : candidates) {
            if (candidate == null) continue;
            Matcher matcher = ORDER_CODE_PATTERN.matcher(candidate);
            if (matcher.find()) {
                String found = matcher.group();
                // Keep original format but ensure uppercase (ORD_1763669579008 -> ORD_1763669579008)
                return found.toUpperCase();
            }
        }
        return null;
    }

    private void saveTransferHistory(SepayTransferRequest request, String orderCode, boolean amountMatches) {
        TransferHistoryEntity transferHistory = new TransferHistoryEntity();
        
        transferHistory.setOrderCode(orderCode);
        transferHistory.setPaymentMethod(TransferHistoryEntity.PaymentMethod.QR); // Default to QR for bank transfers
        transferHistory.setTransferAmount(request.getTransferAmount());
        
        // Create comprehensive description for SEPay transfer
        StringBuilder desc = new StringBuilder();
        desc.append("SEPay Transfer");
        if (request.getGateway() != null) desc.append(" - Gateway: ").append(request.getGateway());
        if (request.getCode() != null) desc.append(" - Code: ").append(request.getCode());
        if (request.getReferenceCode() != null) desc.append(" - Ref: ").append(request.getReferenceCode());
        if (request.getContent() != null) desc.append(" - Content: ").append(request.getContent());
        if (request.getAccountNumber() != null) desc.append(" - Account: ").append(request.getAccountNumber());
        
        transferHistory.setDescription(desc.toString());

        transferHistoryRepository.save(transferHistory);
    }
}