package com.devsoga.BookStore_V2.dtos;

import lombok.Builder;
import lombok.Data;

public class PaymentDTO {

    @Data
    @Builder
    public static class VNPayResponse {
        private String code;
        private String message;
        private String paymentUrl;
    }
}
