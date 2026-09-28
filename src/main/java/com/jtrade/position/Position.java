package com.jtrade.position;
import java.math.BigDecimal;

public class Position {
    private final String accountId;
    private final String symbol;
    private final int quantity;
    private final BigDecimal averagePrice;

    public Position(String accountId, String symbol, int quantity, BigDecimal averagePrice) {
        validateNonNegative(averagePrice, "averagePrice");
        if(accountId == null) throw new NullPointerException("accountId " + accountId + " cannot be null.");
        else if(accountId.isBlank()) throw new IllegalArgumentException("accountId " + accountId + " cannot be blank.");

        if(symbol == null) throw new NullPointerException("symbol " + symbol + " cannot be null");
        else if(symbol.isBlank()) throw new IllegalArgumentException("symbol " + symbol + " cannot be blank");

        if(quantity == 0) throw new IllegalArgumentException("quantity " + quantity + " cannot be 0");
        else if(quantity < 0) throw new IllegalArgumentException("quantity " + quantity + " cannot be negative");
        
        this.accountId = accountId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    // Getters
    public String getAccountId() { return accountId; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public BigDecimal getAveragePrice() { return averagePrice; }

    // Private Helper Method
    private void validateNonNegative(BigDecimal value, String fieldName) {
        // The signum() method in BigDecimal returns -1 if the number is negative, 
        // 0 if it is zero, and 1 if it is positive.
        // Also checks for null to avoid a NullPointerException on .signum()
        if (value == null) {
            throw new NullPointerException(fieldName + " cannot be null.");
        }
        if (value.signum() == -1) {
            throw new IllegalArgumentException(fieldName + " cannot be negative: " + value);
        }
    }

    // Market value of a share
    public BigDecimal marketValue (BigDecimal currentPrice) {
        validateNonNegative(currentPrice, "currentPrice");
        BigDecimal marketValue = currentPrice.multiply(BigDecimal.valueOf(quantity));
        return marketValue;
    }

    // The Unrealise P&L of a share
    public BigDecimal unrealisedPnL (BigDecimal currentPrice) {
        validateNonNegative(currentPrice, "currentPrice");
        BigDecimal unrealisedPnl = (currentPrice.subtract(averagePrice)).multiply(BigDecimal.valueOf(quantity));
        return unrealisedPnl;
    }

    @Override 
    public String toString() {
        return "Position{" +
            "accountId='" + accountId + '\'' +
            ", symbol='" + symbol + '\'' +
            ", quantity=" + quantity +
            ", averagePrice=" + averagePrice +
            '}';
    } 

}
