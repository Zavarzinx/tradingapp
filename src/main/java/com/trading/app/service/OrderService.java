package com.trading.app.service;

import com.trading.app.entity.Order;
import org.bson.types.ObjectId;

public interface OrderService {
    ObjectId placeNewOrder(Order order);

    void cancelOrder(Order order);
}
