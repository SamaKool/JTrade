package com.jtrade.risk;

import com.jtrade.account.Account;
import com.jtrade.order.Order;

public interface RiskRule {
    RiskResult evaluate(Order order, Account account);
}