package com.devsoga.BookStore_V2.enties;

public enum OrderStatus {
    PENDING_CONFIRMATION("Pending Confirmation"),
    CONFIRMED("Confirmed"),
    PROCESSING("Processing"),
    SHIPPING("Shipping"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled"),
    RETURNED("Returned");

    private final String dbValue;

    OrderStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static OrderStatus fromDb(String db) {
        if (db == null) return null;
        for (OrderStatus s : values()) {
            if (s.dbValue.equalsIgnoreCase(db)) return s;
        }
        try { return OrderStatus.valueOf(db); } catch (Exception ex) { return null; }
    }
}
