package com.jtrade.order;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import com.jtrade.exception.InvalidOrderException;

public class InMemoryOrderRepository implements OrderRepository{
    private final Map<String, Order> orders = new HashMap<>();

    @Override
    public void save (Order order) {
        if (order == null) throw new InvalidOrderException("Order cannot be null.");
        if (orders.containsKey(order.getOrderId())) throw new InvalidOrderException("Duplicate Order ID: " + order.getOrderId());
        
        orders.put(order.getOrderId(), order);
    }

    @Override
    public Optional<Order> findById (String orderId) {
        if (orderId == null) throw new NullPointerException("Order ID cannot be null.");
        if (orderId.isBlank()) throw new IllegalArgumentException("Order ID cannot be blank.");
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public List<Order> findAll() {
        return List.copyOf(orders.values());
    }

    @Override
    public boolean delete (String orderId) {
        if (orderId == null) throw new NullPointerException("Order ID cannot be null.");
        if (orderId.isBlank()) throw new IllegalArgumentException("Order ID cannot be blank.");
        
        if (orders.remove(orderId) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean exists (String orderId) {
        if (orderId == null) throw new NullPointerException("Order ID cannot be null.");
        if (orderId.isBlank()) throw new IllegalArgumentException("Order ID cannot be blank.");

        if (orders.containsKey(orderId)) return true;
        return false;
    }
}
