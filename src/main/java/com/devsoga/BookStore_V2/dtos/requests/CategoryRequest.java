package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private String categoryCode; // optional, will be generated if missing
    private String categoryName;
    private String categoryType;
    private String description;
}
