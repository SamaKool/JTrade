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

        // System.out.println(accounts.findById("ACC-001"));

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
        
        repository.save(Order_1);
        repository.save(Order_2);
        repository.save(Order_3);

        System.out.println(repository.findAll());
        
        // if (repository.exists("ORD-001")) {
        //     System.out.println("Order_1 Exists !!!!!!!!!!!!!!");
        // }
        
        // repository.delete("ORD-001");
        // if (repository.exists("ORD-001")) {
        //     System.out.println("Order_1 Exists.........T-T");
        // }
        // else System.out.println("Order Does Not Exist !!!!!!!");

        // System.out.println(Order_1.toString());

        OrderStateMachine m1 = new OrderStateMachine();
        OrderStatus status = OrderStatus.PENDING_RISK;

        m1.transition(Order_1, status);
        System.out.println("Can change status: true");
        System.out.println("Order_1 Status: " + Order_1.getStatus());

        List<RiskRule> riskEngine = new ArrayList<>();
        riskEngine.add(new MaxOrderSizeRule());
        riskEngine.add(new BuyingPowerRule());

        RiskEngine rule = new RiskEngine(riskEngine);
        RiskResult reason = rule.evaluateOrder(Order_1, account_1);
        System.out.println(reason);
        
        
        }
    }
