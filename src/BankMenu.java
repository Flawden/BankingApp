import data.User;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankMenu {

    private Bank bank;

    BankMenu(Bank bank) {
        this.bank = bank;
    }

    public void showStartMenu() {
        String answer = "";

        System.out.println("Registration and authorization panel:");
        System.out.println("Select one: \n" +
                "1. Login\n" +
                "2. Register\n" +
                "3. Exit\n");

       BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        try {
            answer = rd.readLine();
        } catch (IOException e) {
            System.out.println("Access error");
        }

        if(answer.equals("1")) {
            showLogin();
        } else if(answer.equals("2")) {
            showRegister();
        } else if(answer.equals("3")) {
            System.exit(0);
        } else {
            System.out.println("Incorrect value. Please, try again!\n");
        }
    }

    public void showBankMenu() {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String answer = "";

            System.out.println("\nSelect one: \n" +
                    "1. Show my info\n" +
                    "2. Create Loan\n" +
                    "3. Create Debit card\n" +
                    "4. Exit\n");

            try {
                answer = rd.readLine();
            } catch (IOException e) {
                System.out.println("Incorrect value");
            }

            if (answer.equals("1")) {
                //Invoke show method
                System.out.println(bank.getUser());
            } else if (answer.equals("2")) {
                showLoan();
            } else if (answer.equals("3")) {
                // Invoke Debit card's creation method
            } else if (answer.equals("4")) {
                bank.userExit();
                break;
            }

        }

    }

    private void showLogin() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        String email = "";
        String password = "";
        boolean isCorrect = false;

        while (isCorrect == false) {
                try {
                    System.out.println("Enter your email");
                    email = rd.readLine();
                    System.out.println("Enter your password");
                    password = rd.readLine();
                }catch (IOException e) {
                    System.out.println("Invalid value");
                }


            isCorrect = bank.doLogin(email, password);
            if (isCorrect == true) {
                break;
            } else {
                System.out.println("Incorrect login or password.");
                return;
            }


        }

        showBankMenu();

    }

    private void showRegister() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD.MM.YYYY");
        String firstName = "";
        String lastName = "";
        String eMail = "";
        String password = "";
        Date birthdate;
        boolean gender = false;

        while (true) {
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter your name");
                firstName = rd.readLine();

                System.out.println("Enter your last name");
                lastName = rd.readLine();

                System.out.println("Enter your E-mail");
                eMail = rd.readLine();

                System.out.println("Enter your password");
                password = rd.readLine();

                System.out.println("Enter your birthday date. DD.MM.YYYY");
                birthdate = dateFormat.parse(rd.readLine());

                System.out.println("Enter your Gender (0 for Male, 1 for Female)");
                gender = Boolean.parseBoolean(rd.readLine());

                break;

            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        }

        User user = new User(firstName, lastName, eMail, password, birthdate, gender);
        bank.doRegister(user);

    }

    private void showLoan() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        double summ = 0;
        int creditTerm = 0;
        boolean isCorrect = false;

        System.out.println("Select product:");

        while (isCorrect == false) {

        showLoanList();

        try {
            answer = rd.readLine();
        } catch (IOException e) {
            System.out.println("Error");
        }

        System.out.println();

        isCorrect = bank.doLoan(answer);
        }


    }

    private void showLoanList() {
        System.out.println("1. Loan \"Car Owner\". Interest rate 9% per annum.\n " +
                "The loan is available in the amount of 100 thousand to 10 million.\n " +
                "For a period of 1 to 10 years.\n" +

                "2. Loan \"New house\". Interest rate 6% per annum.\n " +
                "The loan is available in amounts from 1 million to 5 million.\n " +
                "For a period of 1 to 20 years.\n" +

                "3. Payday loan. Interest rate 25% per annum.\n " +
                "The loan is available in amounts from 1 thousand to 50 thousand.\n " +
                "For a period of 1 month to 5 months\n" +

                "4. Exit\n\n");
    }

    public boolean showCurrentLoanTerms(double sum, int term, int interestRate, double sumPerMonth, double totalSum) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";

        System.out.println("Let's repeat the terms of the loan:\n" +
                "1) Interest rate: " + interestRate + " % per year\n" +
                "2) Loan term: " + term + " month\n" +
                "3) Monthly payment amount: " + sumPerMonth + " rub\n" +
                "4) Loan amount " + sum + " rub\n" +
                "5) Payment amount: " + totalSum + " rub\n\n");

        System.out.println("Do you agree with the terms?\n" +
                "Enter \"Yes\" to agree\n" +
                "Enter something else to opt out");

        try {
            answer = rd.readLine().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (answer.equals("yes")) {
            return true;
        }
        else {
            return false;
        }

    }

    private void showDebitCard() {}

}
