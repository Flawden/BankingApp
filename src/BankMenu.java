import data.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BankMenu implements Serializable {

    private User user;
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
        } else {
            System.exit(0);
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
                System.out.println(user);
            } else if (answer.equals("2")) {
                // Indoke loan's creation method.
            } else if (answer.equals("3")) {
                // Invoke Debit card's creation method
            } else if (answer.equals("4")) {
                user = null;
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


            isCorrect = doLogin(email, password);
            if (isCorrect == true) {
                break;
            } else {
                System.out.println("Incorrect login or password. Please, try again.");
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
        doRegister(user);

    }

    private boolean doLogin(String email, String password) {
        boolean isCorrect = false;
        for (User user: bank.getUserList()) {
            if (user.geteMail().equals(email)) {
                if(user.getPassword().equals(password)) {
                    isCorrect = true;
                    this.user = user;
                    break;
                }
            }
        }
        return isCorrect;
    }

    private void doRegister(User user) {
        bank.getUserList().add(user);
        bank.serializeUsers(bank.getUserList());
    }

}
