package com.devsoga.BookStore_V2.exceptions;

import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseRespone> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));
        BaseRespone resp = new BaseRespone();
        resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
        resp.setMessage(msg);
        resp.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseRespone> handleConstraintViolation(ConstraintViolationException ex) {
        String msg = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining("; "));
        BaseRespone resp = new BaseRespone();
        resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
        resp.setMessage(msg);
        resp.setData(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<String> handlingRunTimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
