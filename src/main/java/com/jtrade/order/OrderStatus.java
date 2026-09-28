package com.jtrade.order;

public enum OrderStatus {
    NEW,
    PENDING_RISK,
    APPROVED,
    REJECTED,
    SENT,
    PARTIALLY_FILLED,
    FILLED,
    CANCELLED
}