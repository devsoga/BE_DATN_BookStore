package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerTypeRequest {
    private String customerTypeCode;
    private String customerTypeName;
    private String promotionCode;
    private String description;
}
