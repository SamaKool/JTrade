package com.jtrade.risk;
import com.jtrade.order.Order;
import com.jtrade.account.Account;
import java.math.BigDecimal;

public class MaxOrderSizeRule implements RiskRule{
    @Override 
    public RiskResult evaluate(Order order, Account account) {
        BigDecimal notionalValue =
        order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));

        if (notionalValue.compareTo(account.getMaxOrderValue()) > 0) {
            return new RiskResult(false, "Order value exceeds maximum order value.");
        }

        return new RiskResult(true, "Order value is within maximum order value.");
    }
}
