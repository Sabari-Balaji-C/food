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
    private WalletRepository walletRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
                wallet.setWalSource(rs.getString("WAL_SOURCE"));
                return wallet;
            }
        });
    }

    public double getWalletAmount(int custId, String walSource) {
        String sql = "SELECT WAL_AMOUNT FROM wallet WHERE CUS_ID = ? AND WAL_SOURCE = ?";
        Double amount = jdbcTemplate.queryForObject(sql, new Object[]{custId, walSource}, Double.class);
        return amount;
    }

    // Update wallet balance after the order is placed
    public void updateWalletAmount(int custId, String walSource, double newAmount) {
        String sql = "UPDATE wallet SET WAL_AMOUNT = ? WHERE CUS_ID = ? AND WAL_SOURCE = ?";
        jdbcTemplate.update(sql, newAmount, custId, walSource);
    }
    
    public List<Wallet> showAllWallets() {
        return walletRepo.findAll();
    }
    /*public List<Wallet> searchWallet(int walletId) {
        String sql = "SELECT * FROM wallet WHERE WAL_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{walletId}, new RowMapper<Wallet>() {

            @Override
            public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
                Wallet wallet = new Wallet();
                wallet.setWalletId(rs.getInt("WAL_ID"));
                wallet.setCustId(rs.getInt("CUS_ID"));
                wallet.setWalAmount(rs.getDouble("WAL_AMOUNT"));
                wallet.setWalSource(rs.getString("WAL_SOURCE"));
                return wallet;
            }
        });
    }*/
    
}
