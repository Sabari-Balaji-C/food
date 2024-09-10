package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class WalletTest {

    @InjectMocks
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddWallet() {
        Wallet wallet = new Wallet(1, 1, 4215.00, "PAYTM");
        walletService.addWallet(wallet);
        verify(walletRepo, times(1)).save(wallet);
    }

    @Test
    public void testShowWalletByCustomer() {
        Wallet wallet1 = new Wallet(1, 1, 4215.00, "PAYTM");
        Wallet wallet2 = new Wallet(2, 2, 3000.00, "DEBIT_CARD");
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Arrays.asList(wallet1, wallet2));
        List<Wallet> wallets = walletService.showWalletByCustomer(1);
        assertEquals(2, wallets.size());
    }

    @Test
    public void testGetWalletAmount() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Double.class)))
                .thenReturn(4215.00);
        double amount = walletService.getWalletAmount(1, "PAYTM");
        assertEquals(4215.00, amount);
    }

    @Test
    public void testUpdateWalletAmount() {
        walletService.updateWalletAmount(1, "CREDIT_CARD", 6000.00);
        verify(jdbcTemplate, times(1)).update(anyString(), anyDouble(), anyInt(), anyString());
    }

    @Test
    public void testShowAllWallets() {
        Wallet wallet1 = new Wallet(1, 1, 4215.00, "PAYTM");
        Wallet wallet2 = new Wallet(2, 2, 3000.00, "DEBIT_CARD");

        when(walletRepo.findAll()).thenReturn(Arrays.asList(wallet1, wallet2));
        List<Wallet> wallets = walletService.showAllWallets();
        assertEquals(2, wallets.size());
    }
}
