package com.devsoga.BookStore_V2.services;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VnPayOrderCache {

    private final Map<String, String> txnRefToOrder = new ConcurrentHashMap<>();

    public void put(String txnRef, String orderCode) {
        if (txnRef == null || orderCode == null) return;
        txnRefToOrder.put(txnRef, orderCode);
    }

    public String get(String txnRef) {
        if (txnRef == null) return null;
        return txnRefToOrder.get(txnRef);
    }

    public void remove(String txnRef) {
        if (txnRef == null) return;
        txnRefToOrder.remove(txnRef);
    }
}
