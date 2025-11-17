package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerTypeResponse {
    private int id;
    private String customerTypeCode;
    private String customerTypeName;
    private String promotionCode;
    private String description;
}
