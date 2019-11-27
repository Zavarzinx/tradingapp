package com.trading.app.repository;

import com.trading.app.entity.Currency;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository extends MongoRepository<Currency, ObjectId> {
    Currency findCurrencyByName(String name);
}
