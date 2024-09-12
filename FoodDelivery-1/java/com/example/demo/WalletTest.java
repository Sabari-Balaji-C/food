package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WalletTest {

    @Test
    public void testConstructor() {
        // Default constructor
        Wallet wallet = new Wallet();
        assertNotNull(wallet);

        // Parameterized constructor
        Wallet walletNew = new Wallet(1, 1, 4215.00, "PAYTM");
        assertNotNull(walletNew);
        assertEquals(1, walletNew.getWalletId());
        assertEquals(1, walletNew.getCustId());
        assertEquals(4215.00, walletNew.getWalAmount());
        assertEquals("PAYTM", walletNew.getWalSource());
    }

    @Test
    public void testGetterSetter() {
        Wallet wallet = new Wallet();
        wallet.setWalletId(1);
        wallet.setCustId(1);
        wallet.setWalAmount(4215.00);
        wallet.setWalSource("PAYTM");

        assertEquals(1, wallet.getWalletId());
        assertEquals(1, wallet.getCustId());
        assertEquals(4215.00, wallet.getWalAmount());
        assertEquals("PAYTM", wallet.getWalSource());
    }

    
}
