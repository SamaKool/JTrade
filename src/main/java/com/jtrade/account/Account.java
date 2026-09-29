package com.jtrade.account;
import java.math.BigDecimal;

public class Account {
    private final String accountId;
    private BigDecimal availableCash = BigDecimal.ZERO;
    private BigDecimal maxOrderValue = BigDecimal.ZERO;
    private BigDecimal maxPositionValue = BigDecimal.ZERO;
    private BigDecimal maxDailyLoss = BigDecimal.ZERO;

    public Account(String accountId, BigDecimal availableCash, BigDecimal maxOrderValue, BigDecimal maxPositionValue, BigDecimal maxDailyLoss) {
        if (accountId == null) {
            throw new NullPointerException("Account Id: " + accountId + ", cannot be a null value!");
        }
        if (accountId.isBlank()) {
            throw new IllegalArgumentException("Account Id: " + accountId + ", cannot be left blank.");
        }
        
        validateNonNegative(availableCash, "availableCash");
        validateNonNegative(maxOrderValue, "maxOrderValue");
        validateNonNegative(maxPositionValue, "maxPositionValue");
        validateNonNegative(maxDailyLoss, "maxDailyLoss");
        
        this.accountId = accountId;
        this.availableCash = availableCash;
        this.maxOrderValue = maxOrderValue;
        this.maxPositionValue = maxPositionValue;
        this.maxDailyLoss = maxDailyLoss;
    }
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

    // Getters
    public String getAccountId() { return accountId; }
    public BigDecimal getAvailableCash() { return availableCash; }
    public BigDecimal getMaxOrderValue() { return maxOrderValue; }
    public BigDecimal getMaxPositionValue() { return maxPositionValue; }
    public BigDecimal getMaxDailyLoss() { return maxDailyLoss; }

    public void addCash(BigDecimal amount){
        validateNonNegative(amount, "amount");
        availableCash = availableCash.add(amount);
    }

    public void deductCash(BigDecimal amount) {
        validateNonNegative(amount, "amount");
        if((availableCash.subtract(amount)).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance!");
        }
        else availableCash = availableCash.subtract(amount);
    }

    public boolean hasSufficientCash(BigDecimal amount) {
        validateNonNegative(amount, "amount");
        if(amount.compareTo(availableCash) > 0) {
            return false;
        }
        else return true;
    }

    @Override 
    public String toString() {
        return "Account{" +
            "accountId='" + accountId + '\'' +
            ", availableCash=" + availableCash +
            ", maxOrderValue=" + maxOrderValue +
            ", maxPositionValue=" + maxPositionValue +
            ", maxDailyLoss=" + maxDailyLoss +
            '}';
    }
    
}