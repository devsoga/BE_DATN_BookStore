package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRespone {
    private String categoryCode;
    private String categoryName;
    private String categoryType;
    private String description;
}
