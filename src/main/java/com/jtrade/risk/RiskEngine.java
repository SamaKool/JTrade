package com.jtrade.risk;
import java.util.List;
import com.jtrade.order.Order;
import com.jtrade.account.Account;

public class RiskEngine {
    private final List<RiskRule> rules;

    public RiskEngine(List<RiskRule> rules) {
        this.rules = rules;
    }

    public RiskResult evaluateOrder (Order order, Account account) {
        for (RiskRule rule : rules) {
            RiskResult result = rule.evaluate(order, account);
            if (!result.getApproved()) {
                return result;
            }
        }
        return new RiskResult(true, "All risk checks passed.");
    }
}
