package com.trading.app.service.imp;

import com.trading.app.entity.Currency;
import com.trading.app.repository.CurrencyRepository;
import com.trading.app.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
   private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    @Override
    public Currency getCurrencyByName(String name) {
        return currencyRepository.findCurrencyByName(name);
    }

    @Override
    public List<Currency> getAllAvailableCurrency() {
        return currencyRepository.findAll();
    }

    @Override
    public void deleteCurrency(Currency currency) {
        currencyRepository.delete(currency);
    }

    @Override
    public Currency addCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency updateCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }
}
