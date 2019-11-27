package com.trading.app.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private ObjectId id;
    
    private int orderNumber;

    private Currency currencyFrom;

    private Currency currencyTo;
}
