package com.trading.app.controller;

import com.trading.app.entity.Currency;
import com.trading.app.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public ResponseEntity<Currency> createNewCurrency(@RequestBody Currency currency) {
        return new ResponseEntity<>(currencyService.addCurrency(currency), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAllAvailableCurrencies() {
        return new ResponseEntity<>(currencyService.getAllAvailableCurrency(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Currency> updateCurrency(@RequestBody Currency currency) {
        return new ResponseEntity<>(currencyService.updateCurrency(currency), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCurrency(@RequestBody Currency currency) {
        currencyService.deleteCurrency(currency);
        return ResponseEntity.noContent().build();
    }

}
