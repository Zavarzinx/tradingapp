package com.trading.app.repository;

import com.trading.app.entity.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findOrdersByDoneFalse();

    List<Order> findOrdersByDoneTrue();
}
