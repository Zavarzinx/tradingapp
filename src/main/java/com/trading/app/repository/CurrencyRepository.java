package com.trading.app.repository;

import com.trading.app.entity.Currency;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository extends MongoRepository<Currency, String> {
    Currency findCurrencyByName(String name);
}
