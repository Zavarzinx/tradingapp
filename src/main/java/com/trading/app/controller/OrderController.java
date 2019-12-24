package com.trading.app.controller;

import com.trading.app.dto.OrderDto;
import com.trading.app.entity.Order;
import com.trading.app.entity.User;
import com.trading.app.service.CurrencyService;
import com.trading.app.service.OrderService;
import com.trading.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    private final UserService userService;

    private final CurrencyService currencyService;

    public OrderController(OrderService orderService, UserService userService, CurrencyService currencyService) {
        this.orderService = orderService;
        this.userService = userService;
        this.currencyService = currencyService;
    }

    @PostMapping
    public ResponseEntity<Order> placeNewOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        Order placedOrder =  orderService.placeNewOrder(OrderDto.toOrder(orderDto,
                currencyService.getCurrencyByName(orderDto.getCurrencyFrom()),
                        currencyService.getCurrencyByName(orderDto.getCurrencyTo())));
        user.getOrders().add(placedOrder);
        userService.save(user);
       return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Void> cancelOrder(@RequestBody Order order, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        user.getOrders().remove(order);
        userService.save(user);
        orderService.cancelOrder(order);
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/current")
    public ResponseEntity<List<Order>> getAllCurrentOrders() {
       return new ResponseEntity<>(orderService.getAllCurrentOrders(), HttpStatus.OK);
    }

    @GetMapping("/all/done")
    public ResponseEntity<List<Order>> getAllDoneOrders() {
        return new ResponseEntity<>(orderService.getAllFullfillOrders(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping("/fill")
    public ResponseEntity<Order> fillOrder(@RequestBody Order order, @RequestParam long amount, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        if (amount <= 0) {
            throw new IllegalArgumentException("incorrect amount");
        }
        Order filledOrder = orderService.fillOrder(order, amount);
        user.getOrders().add(filledOrder);
        userService.save(user);
        return new ResponseEntity<>(filledOrder, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @RequestBody Order orderFromDB, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        BeanUtils.copyProperties(order, orderFromDB,"id");
        user.getOrders().remove(orderFromDB);
        user.getOrders().add(order);
        userService.save(user);
        return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
    }

}
