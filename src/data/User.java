package data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

    static final long serialVersionUID = 812943703942L;
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;
    private Date birthdate;
    private boolean gender;
    private List<Loan> loanList = new ArrayList<Loan>();
    private List<DebitCard> debitCardList = new ArrayList<DebitCard>();

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    private double balance;


    public User(String firstName, String lastName, String eMail, String password, Date birthdate, boolean gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.balance = 0;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surName) {
        this.lastName = surName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(Date date) {
        this.birthdate = date;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public List<DebitCard> getDebitCardList() {
        return debitCardList;
    }

    public void setDebitCardList(List<DebitCard> debitCardList) {
        this.debitCardList = debitCardList;
    }


    //In the future, for the output of lists, I will make separate methods, but for now...
    @Override
    public String toString() {
        return "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "E-mail: " + eMail + "\n" +
                "Password: " + password + "\n" +
                "Date of Birth: " + birthdate + "\n" +
                "Gender: " + gender + "\n" +
                "Loan list: " + loanList + "\n" +
                "Debit Card List: " + debitCardList + "\n\n" +
                "Balance: " + balance + "\n";
    }

}
