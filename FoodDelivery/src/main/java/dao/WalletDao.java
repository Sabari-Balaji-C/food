package dao;
import entity.Wallet;

import java.sql.SQLException;
import java.util.List;

public interface WalletDao {
	void addWallet(Wallet wallet) throws ClassNotFoundException, SQLException;
    Wallet searchWalletByCustomerId(int custId)throws ClassNotFoundException, SQLException;
    void updateWalletAmount(int custId, double newAmount)throws ClassNotFoundException, SQLException;
    List<Wallet> getAllWallets()throws ClassNotFoundException, SQLException;

}
