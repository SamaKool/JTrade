package com.jtrade.risk;
import com.jtrade.order.Order;
import com.jtrade.account.Account;
import java.math.BigDecimal;

public class BuyingPowerRule implements RiskRule{
    @Override 
    public RiskResult evaluate(Order order, Account account) {
        BigDecimal notionalValue =
        order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));

        if (!account.hasSufficientCash(notionalValue)) {
            return new RiskResult(false, "BuyingPowerRule: Order value exceeds account balance.");
        }

        return new RiskResult(true, "BuyingPowerRule: Order value is within account balance.");
    }
}