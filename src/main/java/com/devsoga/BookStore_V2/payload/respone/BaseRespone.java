package com.devsoga.BookStore_V2.payload.respone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRespone {
    private int statusCode;
    private String message;
    private Object data;
}
