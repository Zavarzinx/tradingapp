package com.trading.app.controller;

import com.trading.app.entity.Currency;
import com.trading.app.entity.User;
import com.trading.app.entity.Wallet;
import com.trading.app.service.UserService;
import com.trading.app.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;

    private final UserService userService;

    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Wallet> addCurrency(@RequestBody Currency currency, @RequestBody Wallet wallet, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        walletService.addCurrency(currency, wallet);
        user.getWallet().addCurrency(currency);
        userService.save(user);
        return new ResponseEntity<>(walletService.addCurrency(currency, wallet), HttpStatus.OK) ;
    }

    @PutMapping
    public ResponseEntity<Wallet> updateWallet(@RequestBody Wallet wallet, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        user.setWallet(wallet);
        userService.save(user);
        return new ResponseEntity<>(walletService.updateWallet(wallet), HttpStatus.OK);
    }
}
