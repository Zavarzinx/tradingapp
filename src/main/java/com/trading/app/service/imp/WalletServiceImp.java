package com.trading.app.service.imp;

import com.trading.app.entity.Currency;
import com.trading.app.entity.Wallet;
import com.trading.app.repository.WalletRepository;
import com.trading.app.service.WalletService;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImp implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImp(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet addCurrency(Currency currency, Wallet wallet) {
        wallet.addCurrency(currency);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
       return walletRepository.save(wallet);
    }
}
