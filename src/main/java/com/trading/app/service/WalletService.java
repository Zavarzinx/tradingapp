package com.trading.app.service;

import com.trading.app.entity.Currency;
import com.trading.app.entity.Wallet;

public interface WalletService {
    Wallet addCurrency(Currency currency, Wallet wallet);

    Wallet updateWallet(Wallet wallet);

}
