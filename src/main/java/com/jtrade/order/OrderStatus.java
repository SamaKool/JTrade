package com.jtrade.order;

enum OrderStatus {
    NEW,
    PENDING_RISK,
    APPROVED,
    REJECTED,
    SENT,
    PARTIALLY_FILLED,
    FILLED,
    CANCELLED
}