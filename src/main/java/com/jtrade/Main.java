package com.jtrade;
import com.jtrade.account.Account;
import com.jtrade.order.Order;
import com.jtrade.order.OrderSide;
import com.jtrade.order.OrderStateMachine;
import com.jtrade.order.OrderStatus;
import com.jtrade.order.OrderType;
import com.jtrade.risk.BuyingPowerRule;
import com.jtrade.risk.MaxOrderSizeRule;
import com.jtrade.risk.RiskEngine;
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

        Order Order_1 = new Order(
            "ORD-001",
            "ACC-001",
            "RELIANCE",
            OrderSide.BUY,
            OrderType.LIMIT,
            100,
            new BigDecimal("2900")
        );

        System.out.println(Order_1.toString());

        OrderStateMachine m1 = new OrderStateMachine();
        OrderStatus status = OrderStatus.FILLED;

        m1.transition(Order_1, status);
        System.out.println("Can change status: true");
        System.out.println("Order_1 Status: " + Order_1.getStatus());

        List<RiskRule> riskEngine = new ArrayList<>();
        riskEngine.add(new MaxOrderSizeRule());
        riskEngine.add(new BuyingPowerRule());

        RiskEngine rule = new RiskEngine(riskEngine);
        RiskResult reason = rule.evaluateOrder(Order_1, account);
        System.out.println(reason);

        /*
        Order Order_2 = new Order(
            "ORD-002",
            "ACC-001",
            "APPL",
            OrderSide.BUY,
            OrderType.LIMIT,
            20,
            new BigDecimal("10000")
        );


        Order Order_3 = new Order(
            "ORD-003",
            "ACC-001",
            "RELIANCE",
            OrderSide.BUY,
            OrderType.LIMIT,
            50,
            new BigDecimal("2900")
        );
        */

        }
    }
