package main;
import java.sql.SQLException;
import entity.Wallet;
import java.util.Scanner;
import dao.WalletDao;
import dao.	WalletDaoImpl;
import java.util.Scanner;
import java.util.List;
	

public class WalletMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WalletDao dao = new WalletDaoImpl();
        
        while (true) {
            System.out.println("\n--- Wallet Management Menu ---");
            System.out.println("1. Add Wallet");
            System.out.println("2. Search Wallet by Customer ID");
            System.out.println("3. Update Wallet Amount");
            System.out.println("4. List All Wallets");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add Wallet
                        System.out.print("Enter Customer ID: ");
                        int custId = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        System.out.print("Enter Wallet Amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine(); // Consume newline

                        System.out.print("Enter Wallet Source (PAYTM, CREDIT_CARD, DEBIT_CARD): ");
                        String source = sc.nextLine();

                        Wallet wallet = new Wallet(custId, 0, amount, source);
                        dao.addWallet(wallet);
                        System.out.println("Wallet added successfully!");
                        break;

                    case 2:
                        // Search Wallet by Customer ID
                        System.out.print("Enter Customer ID: ");
                        int searchCustId = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        Wallet foundWallet = dao.searchWalletByCustomerId(searchCustId);
                        if (foundWallet != null) {
                            System.out.println("Found Wallet: " + foundWallet);
                        } else {
                            System.out.println("No wallet found for Customer ID: " + searchCustId);
                        }
                        break;

                    case 3:
                        // Update Wallet Amount
                        System.out.print("Enter Customer ID: ");
                        int updateCustId = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        System.out.print("Enter New Wallet Amount: ");
                        double newAmount = sc.nextDouble();
                        sc.nextLine(); // Consume newline

                        dao.updateWalletAmount(updateCustId, newAmount);
                        System.out.println("Wallet amount updated successfully!");
                        break;

                    case 4:
                        // List All Wallets
                        List<Wallet> wallets = dao.getAllWallets();
                        if (wallets.isEmpty()) {
                            System.out.println("No wallets available.");
                        } else {
                            System.out.println("All Wallets:");
                            for (Wallet w : wallets) {
                                System.out.println(w);
                            }
                        }
                        break;

                    case 5:
                        // Exit
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch ( ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
