package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VNPAYRespone {
    private String status;
    private String message;
    public String paymentUrl;
}
