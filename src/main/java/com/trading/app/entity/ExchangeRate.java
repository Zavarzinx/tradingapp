package com.trading.app.entity;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRate {
    Map<Currency, Long> rateToCurrencies;
}
