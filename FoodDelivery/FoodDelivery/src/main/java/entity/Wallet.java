package entity;

public class Wallet {
    private int walId;
    private int custId;
    private double walAmount;
    private String walSource;

    // Constructor
    public Wallet(int walId, int custId, double walAmount, String walSource) {
        this.walId = walId;
        this.custId = custId;
        this.walAmount = walAmount;
        this.walSource = walSource;
    }

    // Getters and Setters
    public int getWalId() {
        return walId;
    }

    public void setWalId(int walId) {
        this.walId = walId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public double getWalAmount() {
        return walAmount;
    }

    public void setWalAmount(double walAmount) {
        this.walAmount = walAmount;
    }

    public String getWalSource() {
        return walSource;
    }

    public void setWalSource(String walSource) {
        this.walSource = walSource;
    }

    // toString method
    @Override
    public String toString() {
        return "Wallet{" +
                "walId=" + walId +
                ", custId=" + custId +
                ", walAmount=" + walAmount +
                ", walSource='" + walSource + '\'' +
                '}';
    }
}
