package com.jtrade;
import com.jtrade.account.Account;
import com.jtrade.order.Order;
import com.jtrade.order.OrderSide;
import com.jtrade.order.OrderType;
import com.jtrade.risk.MaxOrderSizeRule;
import com.jtrade.risk.RiskResult;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(
        "ACC-001",
        new BigDecimal("1000000"),
        new BigDecimal("200000"),
        new BigDecimal("500000"),
        new BigDecimal("50000")
);

MaxOrderSizeRule rule = new MaxOrderSizeRule();

Order tooLargeOrder = new Order(
        "ORD-001",
        "ACC-001",
        "RELIANCE",
        OrderSide.BUY,
        OrderType.LIMIT,
        100,
        new BigDecimal("2900")
);

RiskResult rejectedResult = rule.evaluate(tooLargeOrder, account);
System.out.println(rejectedResult);


Order exactOrder = new Order(
    "ORD-002",
    "ACC-001",
    "APPL",
    OrderSide.BUY,
    OrderType.LIMIT,
    20,
    new BigDecimal("10000")
);

RiskResult resultEven = rule.evaluate(exactOrder, account);
System.out.println(resultEven);


Order allowedOrder = new Order(
        "ORD-002",
        "ACC-001",
        "RELIANCE",
        OrderSide.BUY,
        OrderType.LIMIT,
        50,
        new BigDecimal("2900")
);

RiskResult approvedResult = rule.evaluate(allowedOrder, account);
System.out.println(approvedResult);
    }
}
