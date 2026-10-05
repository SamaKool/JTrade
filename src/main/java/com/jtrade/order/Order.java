package com.jtrade.order;
import java.math.BigDecimal;

import com.jtrade.exception.InvalidOrderException;

public class Order {
    private final String orderId;
    private final String accountId;
    private final String symbol;
    private final OrderSide side;
    private final OrderType type;
    private final int quantity;
    private final BigDecimal price; 
    private OrderStatus status = OrderStatus.NEW;
    
    public Order(String orderId, String accountId, String symbol, OrderSide side, OrderType type, int quantity, BigDecimal price) {
        if (orderId == null) throw new InvalidOrderException("OrderId cannot be null.");
        else if (orderId.isBlank()) throw new InvalidOrderException("OrderId cannot be blank.");
        if (side == null) throw new InvalidOrderException("OrderSide value cannot be null.");
        if (type == null) throw new InvalidOrderException("OrderType value cannot be null.");
        if (accountId == null) throw new InvalidOrderException("AccountId cannot be null.");
        if (accountId.isBlank()) throw new InvalidOrderException("AccountId cannot be blank.");
        if (symbol == null) throw new InvalidOrderException("Symbol cannot be null.");
        if (symbol.isBlank()) throw new InvalidOrderException("Symbol cannot be blank.");
        if (price == null) throw new InvalidOrderException("Price cannot be null.");
        if (price.signum() < 0) throw new InvalidOrderException("Price cannot be negative.");
        if (quantity <= 0) throw new InvalidOrderException("Quantity must be greater than zero.");
        if (type == OrderType.LIMIT && price.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidOrderException("LIMIT order price must be strictly greater than zero.");
        this.orderId = orderId;
        this.accountId = accountId;
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    void changeStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    // Getters
    public String getOrderId() { return this.orderId; }
    public String getAccountId() { return this.accountId; }
    public String getSymbol() { return this.symbol; }
    public OrderSide getSide() { return this.side; }
    public OrderType getType() { return this.type; }
    public int getQuantity() { return this.quantity; }
    public BigDecimal getPrice() { return this.price; }
    public OrderStatus getStatus() { return this.status; }

    @Override
    public String toString() {
        return "Order{" +
            "symbol='" + symbol + '\'' +
            ", orderId='" + orderId + '\'' +
            ", accountId='" + accountId + '\'' +
            ", side=" + side +
            ", type=" + type +
            ", quantity=" + quantity +
            ", price=" + price +
            ", status=" + status +
            '}';
    }
}