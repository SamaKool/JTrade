package com.jtrade.order;
import com.jtrade.account.Account;
import com.jtrade.account.AccountRepository;
import com.jtrade.risk.RiskEngine;
import com.jtrade.risk.RiskResult;
import com.jtrade.exception.*;

public class OrderService {
    private final OrderRepository orderRepo;
    private final AccountRepository accRepo;
    private final RiskEngine riskEng;
    private final OrderStateMachine OSM_1;
    public OrderService (OrderRepository orderRepo, AccountRepository accRepo, RiskEngine riskEng, OrderStateMachine OSM_1) {
        this.orderRepo = orderRepo;
        this.accRepo = accRepo;
        this.riskEng = riskEng;
        this.OSM_1 = OSM_1;
    }

    public Order submitOrder(Order order) {
        if (order == null) throw new InvalidOrderException("Order cannot be null.");

        if(orderRepo.exists(order.getOrderId())) throw new InvalidOrderException("Duplicate order: " + order.getOrderId());
        Account account = accRepo.findById(order.getAccountId()).orElseThrow(() -> new AccountNotFoundException("Account not found: " + order.getAccountId()));
        
        OrderStatus status = OrderStatus.PENDING_RISK;
        OSM_1.transition(order, status);

        RiskResult reason = riskEng.evaluateOrder(order, account);

        if(reason.getApproved()) {
            OSM_1.transition(order, OrderStatus.APPROVED);
        }
        else OSM_1.transition(order, OrderStatus.REJECTED);

        orderRepo.save(order);
        return order;
    }
}
