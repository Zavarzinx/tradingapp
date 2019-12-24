package com.trading.app.dto;

import com.trading.app.entity.Currency;
import com.trading.app.entity.Order;
import lombok.Data;

@Data
public class OrderDto {

    private long toSell;

    private String currencyFrom;

    private String currencyTo;

    public static Order toOrder(OrderDto orderDto, Currency from, Currency to) {
        return new Order(orderDto.getToSell(), from, to);
    }

    public OrderDto(long toSell, String currencyFrom, String currencyTo) {
        this.toSell = toSell;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }
    public OrderDto(Order order) {
       this.toSell = order.getToSell();

       this.currencyFrom = order.getCurrencyFrom().getName();

       this.currencyTo = order.getCurrencyTo().getName();
    }
}
