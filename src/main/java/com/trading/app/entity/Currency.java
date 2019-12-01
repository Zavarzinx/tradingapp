package com.trading.app.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document("Currency")
public class Currency {
    @Id
    private String id;

    private String name;

    private ExchangeRate exchangeRate;
}
