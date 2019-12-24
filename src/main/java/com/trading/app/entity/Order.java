package com.trading.app.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private long toSell;

    private long toPay;

    private boolean isDone = false;

    private Currency currencyFrom;

    private Currency currencyTo;

    public Order( long toSell, Currency currencyFrom, Currency currencyTo) {
        this.toSell = toSell;
        this.toPay = toSell * currencyTo.getExchangeRate().getRateToCurrencies().get(currencyTo);
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }
}
