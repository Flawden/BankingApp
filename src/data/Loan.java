package data;

import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable {

    static final long serialVersionUID = 812943703942L;
    private Date issueDate;
    private double summ;
    private double interestRate;
    private double monthlyPayment;
    private int creditTerm;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date date) {
        this.issueDate = date;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(int creditTerm) {
        this.creditTerm = creditTerm;
    }

    @Override
    public String toString() {
        return "Date: " + issueDate + "\n" +
                "Summ: " + issueDate + "\n" +
                "Interest rate: " + issueDate + "\n" +
                "Monthly payment: " + issueDate + "\n" +
                "Credit term: " + issueDate + "\n";
    }
}
