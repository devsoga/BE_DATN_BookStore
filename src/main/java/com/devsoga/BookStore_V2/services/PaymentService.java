package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.configurations.VNPAYConfig;
import com.devsoga.BookStore_V2.dtos.PaymentDTO;
import com.devsoga.BookStore_V2.util.VNPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class PaymentService {

    private final VNPAYConfig vnPayConfig;
    private final VnPayOrderCache vnPayOrderCache;

    @Autowired
    public PaymentService(VNPAYConfig vnPayConfig, VnPayOrderCache vnPayOrderCache) {
        this.vnPayConfig = vnPayConfig;
        this.vnPayOrderCache = vnPayOrderCache;
    }

    public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request) {
        long amount = Long.parseLong(request.getParameter("amount")) * 100L;
        String bankCode = request.getParameter("bankCode");
        String orderCode = request.getParameter("orderCode");

        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();

        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));

        // store mapping txnRef -> orderCode if client provided orderCode
        String txnRef = vnpParamsMap.get("vnp_TxnRef");
        if (orderCode != null && !orderCode.isBlank()) {
            // Keep VNPay txnRef as-is (do not change VNPay parameters) but remember mapping
            vnPayOrderCache.put(txnRef, orderCode);
        }

        // build query url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return PaymentDTO.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl)
                .build();
    }
}
