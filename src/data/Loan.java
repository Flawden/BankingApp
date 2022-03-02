package data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Loan implements Serializable {

    private static final long serialVersionUID = 812943703942L;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Date issueDate;
    private double summ;
    private double interestRate;
    private double monthlyPayment;
    private int creditTerm;

    public Loan(Date issueDate, double summ, double interestRate, double monthlyPayment, int creditTerm) {
        this.issueDate = new Date();
        this.summ = summ;
        this.interestRate = interestRate;
        this.monthlyPayment = monthlyPayment;
        this.creditTerm = creditTerm;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
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
        return "Credit:\n" +
                "Credit for the amount of " + summ + ", for a period of" + creditTerm + " months.\n" +
                "Monthly payment: " + monthlyPayment + " rub\n" +
                "Taken: " + simpleDateFormat.format(issueDate) + "\n";
    }
}
