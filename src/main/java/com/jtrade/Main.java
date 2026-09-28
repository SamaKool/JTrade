package com.jtrade;
import com.jtrade.account.Account;
import com.jtrade.order.Order;
import com.jtrade.order.OrderSide;
import com.jtrade.order.OrderType;
import com.jtrade.risk.BuyingPowerRule;
import com.jtrade.risk.MaxOrderSizeRule;
import com.jtrade.risk.RiskResult;
import com.jtrade.risk.RiskRule;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(
        "ACC-001",
        new BigDecimal("500000"),
        new BigDecimal("1000000"),
        new BigDecimal("580000"),
        new BigDecimal("50000")
    );

    List<RiskRule> rules = new ArrayList<>();
    rules.add(new MaxOrderSizeRule());
    rules.add(new BuyingPowerRule());

    Order tooLargeOrder = new Order(
        "ORD-001",
        "ACC-001",
        "RELIANCE",
        OrderSide.BUY,
        OrderType.LIMIT,
        200,
        new BigDecimal("2900")
    );

    for (RiskRule rule : rules) {
        RiskResult result = rule.evaluate(tooLargeOrder, account);
        System.out.println(result);
    }


    Order exactOrder = new Order(
        "ORD-002",
        "ACC-001",
        "APPL",
        OrderSide.BUY,
        OrderType.LIMIT,
        20,
        new BigDecimal("10000")
    );

    // RiskResult resultEven = rule.evaluate(exactOrder, account);
    // System.out.println(resultEven);


    Order allowedOrder = new Order(
        "ORD-002",
        "ACC-001",
        "RELIANCE",
        OrderSide.BUY,
        OrderType.LIMIT,
        50,
        new BigDecimal("2900")
    );

    // RiskResult approvedResult = rule.evaluate(allowedOrder, account);
    // System.out.println(approvedResult);
        }
    }
