package com.trading.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "currency")
public class Currency {
    @Id
    private String id;

    private String name;

    @JsonIgnore
    private ExchangeRate exchangeRate;

    public Currency(String name, ExchangeRate exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }
}
