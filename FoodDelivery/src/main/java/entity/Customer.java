package entity;

public class Customer {
    private int cusId;
    private String cusName;
    private String cusPhoneNo;
    private String cusUsername;
    private String cusPassword;
    private String cusEmail;

    // Constructor
    public Customer(int cusId, String cusName, String cusPhoneNo, String cusUsername, String cusPassword, String cusEmail) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusPhoneNo = cusPhoneNo;
        this.cusUsername = cusUsername;
        this.cusPassword = cusPassword;
        this.cusEmail = cusEmail;
    }

    // Default Constructor
    public Customer() {
    }

    // Getters and Setters
    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhoneNo() {
        return cusPhoneNo;
    }

    public void setCusPhoneNo(String cusPhoneNo) {
        this.cusPhoneNo = cusPhoneNo;
    }

    public String getCusUsername() {
        return cusUsername;
    }

    public void setCusUsername(String cusUsername) {
        this.cusUsername = cusUsername;
    }

    public String getCusPassword() {
        return cusPassword;
    }

    public void setCusPassword(String cusPassword) {
        this.cusPassword = cusPassword;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }
}
