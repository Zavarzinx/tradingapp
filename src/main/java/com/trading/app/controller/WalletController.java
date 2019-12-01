package com.trading.app.controller;

import com.trading.app.entity.Currency;
import com.trading.app.entity.Wallet;
import com.trading.app.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> addCurrency(@RequestBody Currency currency, @RequestBody Wallet wallet) {
      return new ResponseEntity<>(walletService.addCurrency(currency, wallet), HttpStatus.OK) ;
    }

    @PutMapping
    public ResponseEntity<Wallet> updateWallet(@RequestBody Wallet wallet) {
       return new ResponseEntity<>(walletService.updateWallet(wallet), HttpStatus.OK);
    }
}
