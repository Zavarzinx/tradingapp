package com.trading.app.service;

import com.trading.app.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeNewOrder(Order order);

    void cancelOrder(Order order);

    List<Order> getAllCurrentOrders();

    Order updateOrder(Order order);
}
