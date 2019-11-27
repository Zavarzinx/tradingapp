package com.trading.app.service;

import com.trading.app.entity.Currency;

import java.util.List;

public interface CurrencyService {
   Currency getCurrencyByName(String name);

   List<Currency> getAllAvailableCurrency();

   void deleteCurrency(Currency currency);

   Currency addCurrency(Currency currency);

   Currency updateCurrency(Currency currency);
}
