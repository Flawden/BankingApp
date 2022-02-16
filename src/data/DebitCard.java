package data;

import java.io.Serializable;
import java.util.Date;

public class DebitCard implements Serializable {

    static final long serialVersionUID = 812943703942L;
    private double balance;
    private String cardNumber;
    private Date expirationDate;
    private int cvv;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Balance: " + balance + "\n" +
                "Card number: " + cardNumber + "\n" +
                "Expiration date: " + expirationDate + "\n" +
                "CVV code: " + cvv + "\n";
    }

}
