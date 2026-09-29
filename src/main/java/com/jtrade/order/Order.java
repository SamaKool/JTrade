package com.jtrade.order;
import java.math.BigDecimal;

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
        this.orderId = orderId;
        this.accountId = accountId;
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public void changeStatus(OrderStatus newStatus) {
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