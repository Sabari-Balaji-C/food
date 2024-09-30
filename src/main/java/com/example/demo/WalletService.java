package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired 
    WalletRepository walletRepo;

    @Autowired 
    JdbcTemplate jdbcTemplate;

    public void addWallet(Wallet wallet) {
        walletRepo.save(wallet);
    }

    public List<Wallet> showWalletByCustomer(int custId) {
        String sql = "SELECT * FROM wallet WHERE CUS_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{custId}, new RowMapper<Wallet>() {

            @Override
            public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
                Wallet wallet = new Wallet();
                wallet.setWalletId(rs.getInt("WAL_ID"));
                wallet.setCustId(rs.getInt("CUS_ID"));
                wallet.setWalAmount(rs.getDouble("WAL_AMOUNT"));
                wallet.setWalSource(rs.getString("WAL_SOURCE")); // String instead of Enum
                return wallet;
            }
        });
    }

    public List<Wallet> showAllWallets() {
        return walletRepo.findAll();
    }

    public void updateWalletAmount(int custId, String walSource, double newAmount) {
        String sql = "UPDATE wallet SET WAL_AMOUNT = ? WHERE CUS_ID = ? AND WAL_SOURCE = ?";
        jdbcTemplate.update(sql, newAmount, custId, walSource);
    }
    
    public Wallet searchWallet(int walletId) {
        return walletRepo.findById(walletId).orElse(null);
    }


    public double getWalletAmount(int custId, String walSource) {
        String sql = "SELECT WAL_AMOUNT FROM wallet WHERE CUS_ID = ? AND WAL_SOURCE = ?";
        Double amount = jdbcTemplate.queryForObject(sql, new Object[]{custId, walSource}, Double.class);
        return amount != null ? amount : 0.0;
    }

 

}
