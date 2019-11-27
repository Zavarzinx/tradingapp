package com.trading.app.service.imp;

import com.trading.app.entity.Order;
import com.trading.app.repository.OrderRepository;
import com.trading.app.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ObjectId placeNewOrder(Order order) {
      return orderRepository.save(order).getId();
    }

    @Override
    public void cancelOrder(Order order) {

    }
}
