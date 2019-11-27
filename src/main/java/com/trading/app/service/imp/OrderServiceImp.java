package com.trading.app.service.imp;

import com.trading.app.entity.Order;
import com.trading.app.repository.OrderRepository;
import com.trading.app.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeNewOrder(Order order) {
      return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getAllCurrentOrders() {
       return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Order order) {
       return orderRepository.save(order);
    }
}
