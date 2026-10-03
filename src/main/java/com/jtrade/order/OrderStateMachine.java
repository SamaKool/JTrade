package com.jtrade.order;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.EnumSet;
import java.util.EnumMap;

public class OrderStateMachine {
    public boolean canTransition(OrderStatus from, OrderStatus to) {
        // Switch Case and If-else Implementation
        /*
        switch (from) {
            case NEW:
                if(to == OrderStatus.PENDING_RISK){
                    return true;
                }
                else return false;
            
            case PENDING_RISK:
                if (to == OrderStatus.APPROVED || to == OrderStatus.REJECTED) {
                    return true;
                }
                else return false;
                
            case APPROVED:
                if (to == OrderStatus.SENT) {
                    return true;
                }
                else return false;
                
            case SENT:
                if (to == OrderStatus.PARTIALLY_FILLED || to == OrderStatus.FILLED || to == OrderStatus.CANCELLED) {
                    return true;
                }
                else return false;

            case PARTIALLY_FILLED:
                if (to == OrderStatus.PARTIALLY_FILLED || to == OrderStatus.FILLED || to == OrderStatus.CANCELLED) {
                    return true;
                }
                else return false;
            default:
                return false;
            }
        */

        // Pure Switch Case Implementation:
        /*
        return switch (from) {
            case NEW -> switch (to) {
                case PENDING_RISK -> true;
                default -> false;
            };
            case PENDING_RISK -> switch (to) {
                case APPROVED, REJECTED -> true;
                default -> false;
            };
            case APPROVED -> switch (to) {
                case SENT -> true;
                default -> false;
            };
            case SENT -> switch (to) {
                case PARTIALLY_FILLED, FILLED, CANCELLED -> true;
                default -> false;
            };
            case PARTIALLY_FILLED -> switch (to) {
                case PARTIALLY_FILLED, FILLED, CANCELLED -> true;
                default -> false;
            };
            default -> false;
        };
        */

        // Map-Set implementation for future scalability:
        Map<OrderStatus, Set<OrderStatus>> transitionFrom = new EnumMap<>(OrderStatus.class);
        transitionFrom.put(OrderStatus.NEW, EnumSet.of(OrderStatus.PENDING_RISK));
        transitionFrom.put(OrderStatus.PENDING_RISK, EnumSet.of(OrderStatus.APPROVED, OrderStatus.REJECTED));
        transitionFrom.put(OrderStatus.APPROVED, EnumSet.of(OrderStatus.SENT));
        transitionFrom.put(OrderStatus.SENT, EnumSet.of(OrderStatus.PARTIALLY_FILLED, OrderStatus.FILLED, OrderStatus.CANCELLED));
        transitionFrom.put(OrderStatus.PARTIALLY_FILLED, EnumSet.of(OrderStatus.PARTIALLY_FILLED, OrderStatus.FILLED, OrderStatus.CANCELLED));

        return transitionFrom.getOrDefault(from, Collections.emptySet()).contains(to);
    }


    public void transition(Order order, OrderStatus newStatus) {
        if(canTransition(order.getStatus(), newStatus)) {
            order.changeStatus(newStatus);
        }
        else {
            throw new IllegalStateException("Invalid transition! " + order.getStatus() + " -> " + newStatus);
       }
    }

}
