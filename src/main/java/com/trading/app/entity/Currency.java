package com.trading.app.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class Currency {
    @Id
    private ObjectId id;

    private String name;

    private ExchangeRate exchangeRate;
}
