package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    boolean insertOrder(Order order);

    boolean insertOrderStatus(Order order);
}
