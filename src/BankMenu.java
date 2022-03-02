import data.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BankMenu {

    private final Bank bank;

    public BankMenu(Bank bank) {
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

        if (answer.equals("1")) {
            showLogin();
        } else if (answer.equals("2")) {
            showRegister();
        } else if (answer.equals("3")) {
            System.exit(0);
        } else {
            System.out.println("Incorrect value. Please, try again!\n");
        }
    }

    public void showBankMenu() {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String answer = "";
            boolean isAdmin = bank.getIsAdmin();

            System.out.println("\nSelect one: \n" +
                    "1. Show my info\n" +
                    "2. Create Loan\n" +
                    "3. Create Debit card");

            if (isAdmin) {
                System.out.println("4. Show statistics");
                System.out.println("5. Exit\n");
            } else {
                System.out.println("4. Exit\n");
            }


            try {
                answer = rd.readLine();
            } catch (IOException e) {
                System.out.println("Incorrect value");
            }

            if (answer.equals("1")) {
                System.out.println(bank.getUser());
            } else if (answer.equals("2")) {
                showLoan();
            } else if (answer.equals("3")) {
                showDebitCard();
            } else if (answer.equals("4") && isAdmin) {
                bank.showStatistics();
            } else if (answer.equals("4") || (answer.equals("5") && isAdmin)) {
                bank.userExit();
                break;
            }

        }

    }

    public void printStatistics() {
        System.out.println("1) List of created users in the last 24 hours\n" +
                "2) List of users by number of credits\n" +
                "3) List of users grouped by number of cards\n" +
                "4) Exit. \n");
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
            } catch (IOException e) {
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
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        String firstName = enterName();
        String lastName = enterLastName();
        String eMail = enterEmail();
        String password = enterPassword();
        Date birthdate = enterBirthDate();
        boolean gender = enterGender();

        User user = new User(firstName, lastName, eMail, password, birthdate, gender);
        bank.doRegister(user);
    }

    private String enterName() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String firstName = "";
        while (true) {
            try {
                System.out.println("Enter your name");
                firstName = rd.readLine();
            } catch (IOException e) {
                System.out.println("Server Error");
            }

            if (!firstName.matches("[а-яёА-ЯЁa-zA-Z]+")) {
                System.out.println("The name cannot contain numbers or special characters");
            } else {
                break;
            }
        }

        return firstName;
    }

    private String enterLastName() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String lastName = "";
        while (true) {
            try {
                System.out.println("Enter your last name");
                lastName = rd.readLine();
            } catch (IOException e) {
                System.out.println("Server Error");
            }

            if (!lastName.matches("[а-яёА-ЯЁa-zA-Z]+")) {
                System.out.println("The name cannot contain numbers or special characters");
            } else {
                break;
            }
        }

        return lastName;
    }

    private String enterEmail() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String eMail = "";
        while (true) {
            try {
                System.out.println("Enter your E-mail");
                eMail = rd.readLine();
            } catch (IOException e) {
                System.out.println("Error");
            }

            if (!eMail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                System.out.println("Incorrect E-mail. An example of a valid E-mail: Example@ex.ru");
            } else {
                break;
            }

        }

        return eMail;
    }

    private String enterPassword() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            System.out.println("Enter your password");
            password = rd.readLine();
        } catch (IOException e) {
            System.out.println("Error");
        }

        return  password;
    }

    private Date enterBirthDate() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        int day = 0;
        int month = 0;
        int year = 0;

        while (true) {
            try {
                System.out.println("Enter your birthday day.");
                day = Integer.parseInt(rd.readLine());
                break;
            } catch (IOException e) {
                System.out.println("Server error");
            } catch (NumberFormatException e) {
                System.out.println("Incorrect day. Try again");
            }
        }
        while (true) {
            try {
                System.out.println("Enter your birthday month.");
                month = Integer.parseInt(rd.readLine());
                break;
            } catch (IOException e) {
                System.out.println("Server error");
            } catch (NumberFormatException e) {
                System.out.println("Incorrect month. Try again");
            }
        }
        while (true) {
            try {
                System.out.println("Enter your birthday year.");
                year = Integer.parseInt(rd.readLine());
                break;
            } catch (IOException e) {
                System.out.println("Server error");
            } catch (NumberFormatException e) {
                System.out.println("Incorrect year. Try again");
            }
        }

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.getTime();
    }

    private boolean enterGender() {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        boolean gender = false;

        try {
            System.out.println("Enter your Gender (0 for Male, 1 for Female)");
            gender = Boolean.parseBoolean(rd.readLine());
        } catch (IOException e) {
            System.out.println("Error");
        }

        return gender;
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
        } else {
            return false;
        }

    }

    private void showDebitCard() {
        bank.doCreditCard();
        System.out.println("Debit card was generated.");
    }

}
