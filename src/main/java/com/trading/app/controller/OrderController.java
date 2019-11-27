package com.trading.app.controller;

import com.trading.app.entity.Order;
import com.trading.app.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeNewOrder(@RequestBody Order order) {
       return new ResponseEntity<>(orderService.placeNewOrder(order), HttpStatus.CREATED);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelOrder(@RequestBody Order order) {
        orderService.cancelOrder(order);
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllCurrentOrders() {
       return new ResponseEntity<>(orderService.getAllCurrentOrders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @RequestBody Order orderFromDB) {
        BeanUtils.copyProperties(order, orderFromDB,"id");
        return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
    }

}
