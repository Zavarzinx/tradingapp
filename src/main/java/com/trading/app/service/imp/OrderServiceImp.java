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
       long exchangeRate = order.getCurrencyFrom().getExchangeRate().getRateToCurrencies().get(order.getCurrencyTo());
       order.setToPay(order.getToSell() * exchangeRate);
      return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getAllCurrentOrders() {
       return orderRepository.findOrdersByDoneFalse();
    }

    @Override
    public Order updateOrder(Order order) {
       return orderRepository.save(order);
    }

    @Override
    public Order fillOrder(Order order, long amount) {
       if (order.getToPay() != amount) {
           throw new IllegalArgumentException("you need to pay correct amount to fullfill this order");
       }
       order.setDone(true);
       return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllFullfillOrders() {
       return orderRepository.findOrdersByDoneTrue();
    }
}
