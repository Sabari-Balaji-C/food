package dao;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Wallet;
import util.DBConnection;

public class WalletDaoImpl implements WalletDao{
	public void addWallet(Wallet wallet)throws ClassNotFoundException {
        String sql = "INSERT INTO wallet (CUS_ID, WAL_AMOUNT, WAL_SOURCE) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, wallet.getCustId());
            stmt.setDouble(2, wallet.getWalAmount());
            stmt.setString(3, wallet.getWalSource());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Wallet searchWalletByCustomerId(int custId)throws ClassNotFoundException {
        String sql = "SELECT * FROM wallet WHERE CUS_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, custId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Wallet(rs.getInt("WAL_ID"),
                                      rs.getInt("CUS_ID"),
                                      rs.getDouble("WAL_AMOUNT"),
                                      rs.getString("WAL_SOURCE"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateWalletAmount(int custId, double newAmount)throws ClassNotFoundException {
        String sql = "UPDATE wallet SET WAL_AMOUNT = ? WHERE CUS_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newAmount);
            stmt.setInt(2, custId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Wallet> getAllWallets() throws ClassNotFoundException{
        String sql = "SELECT * FROM wallet";
        List<Wallet> wallets = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                wallets.add(new Wallet(rs.getInt("WAL_ID"),
                                       rs.getInt("CUS_ID"),
                                       rs.getDouble("WAL_AMOUNT"),
                                       rs.getString("WAL_SOURCE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallets;
    }
}


