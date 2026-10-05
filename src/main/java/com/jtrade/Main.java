package com.jtrade;
import com.jtrade.account.Account;
import com.jtrade.account.AccountRepository;
import com.jtrade.account.InMemoryAccountRepository;
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
import com.jtrade.order.InMemoryOrderRepository;
import com.jtrade.order.OrderRepository;
import com.jtrade.order.OrderService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccountRepository accounts = new InMemoryAccountRepository();
        Account account_1 = new Account(
        "ACC-001",
        new BigDecimal("500000"),
        new BigDecimal("1000000"),
        new BigDecimal("580000"),
        new BigDecimal("50000")
        );

        accounts.save(account_1);
        if(accounts.exists("ACC-001")) System.out.println("Account " + account_1.getAccountId() + " Exists !!!!!");
        else System.out.println("Account dont exist.....");

        OrderRepository repository = new InMemoryOrderRepository();

        Order Order_1 = new Order(
            "ORD-001",
            "ACC-001",
            "RELIANCE",
            OrderSide.BUY,
            OrderType.LIMIT,
            100,
            new BigDecimal("2900")
        );
    
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

        System.out.println(Order_1.toString());
        
        OrderStateMachine m1 = new OrderStateMachine();
        
        List<RiskRule> riskEngine = new ArrayList<>();
        riskEngine.add(new MaxOrderSizeRule());
        riskEngine.add(new BuyingPowerRule());
        
        RiskEngine rule = new RiskEngine(riskEngine);
        
        OrderService os1 = new OrderService(repository, accounts, rule, m1);
        Order result = os1.submitOrder(Order_1);
        System.out.println(result.getStatus());

        System.out.println(repository.findAll());
    }
}
