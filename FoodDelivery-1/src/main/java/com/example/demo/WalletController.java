package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping(value="/addWallet")
    public void addWallet(@RequestBody Wallet wallet) {
        walletService.addWallet(wallet);
    }

    @GetMapping(value="/showWalletByCustomer/{custId}")
    public List<Wallet> getWalletByCustomer(@PathVariable int custId) {
        return walletService.showWalletByCustomer(custId);
    }

    @GetMapping(value="/showAllWallets")
    public List<Wallet> getAllWallets() {
        return walletService.showAllWallets();
    }

    @PutMapping(value="/updateWalletAmount/{custId}/{walSource}/{amount}")
    public void updateWalletAmount(@PathVariable int custId, @PathVariable String walSource, @PathVariable double amount) {
        walletService.updateWalletAmount(custId, walSource,amount);
    }

    /*@GetMapping(value="/searchWallet/{walletId}")
    public List<Wallet> searchWallet(@PathVariable int walletId) {
        return walletService.searchWallet(walletId);
    }*/
}
