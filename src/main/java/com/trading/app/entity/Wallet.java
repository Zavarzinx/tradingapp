package com.trading.app.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Document("wallet")
public class Wallet {

    @Id
    String id;

    private Map<Currency, Integer> currencyAmount;

    private List<Currency> currencies = new ArrayList<>();

    public void addCurrency(Currency currency) {
        currencyAmount.put(currency, 0);
        currencies.add(currency);
    }

    public void addAmount(int amount, Currency currency) {
      int newAmount = currencyAmount.get(currency) + amount;
      currencyAmount.put(currency, newAmount);
    }

}
