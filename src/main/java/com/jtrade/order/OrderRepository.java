package com.jtrade.order;
import java.util.Optional;
import java.util.List;
public interface OrderRepository{
    void save (Order order);
    Optional<Order> findById (String orderId);
    List<Order> findAll();
    boolean delete (String orderId);
    boolean exists (String orderId);
}
