import data.DebitCard;
import data.Loan;
import data.User;

import java.io.*;
import java.util.*;

public class Bank {

    private final String dataFile = "Users.dat";

    private List<User> users;
    private User user;
    private BankMenu bankMenu = new BankMenu(this);
    private HashSet<String> cardNumbers = new HashSet<String>();

    public User getUser() {
        return this.user;
    }

    public boolean getIsAdmin() {return user.isAdmin();}

    public void start() {
        deserializeUsers();
        bankMenu.showStartMenu();
    }

    private void serializeUsers(List<User> users) {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            os.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    private void deserializeUsers() {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(dataFile))) {
            this.users = (ArrayList<User>) os.readObject();
        } catch (FileNotFoundException e) {
            this.users = new ArrayList<User>();
            createSuperUser();
            serializeUsers(users);
        } catch (IOException e) {
            System.out.println("Access error");
        } catch (Exception e) {
            System.out.println("Server error");
        }

    }

    private void createSuperUser() {
        user = new User("Admin", "Admin", "Admin@admin.ru", "Admin", new Date(), false, true);
        users.add(user);
    }

    public void showStatistics() {

        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";

        while (true) {

            bankMenu.printStatistics();

            try {
                answer = rd.readLine();
            } catch (IOException e) {
                System.out.println("Error");
            }

            if(answer.equals("1")) {
                showLastDayUserCreated();
            } else if (answer.equals("2")) {
                showUsersWithCredits();
            } else if (answer.equals("3")) {
                showUsersWithDebitCard();
            } else if (answer.equals("4")) {
                break;
            } else {
                System.out.println("Incorrect value. Try again.");
            }

        }

    }

    public void showLastDayUserCreated() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date nowTime = calendar.getTime();

        users.stream()
                .filter(x -> x.getCreatedDate().after(nowTime))
                .forEach(System.out::println);
    }

    public void showUsersWithCredits() {
        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.getLoanList().size()))
                .forEach(System.out::println);
    }

    public void showUsersWithDebitCard() {
        users.stream().sorted(Comparator.comparingInt((User u) -> u.getDebitCardList().size())).forEach(System.out::println);
    }

    public boolean doLogin(String email, String password) {
        boolean isCorrect = false;
        for (User user : users) {
            if (user.geteMail().equals(email) && user.getPassword().equals(password)) {
                isCorrect = true;
                this.user = user;
                break;
            }
        }
        return isCorrect;
    }

    public void doRegister(User user) {
        users.add(user);
        serializeUsers(users);
    }

    public void userExit() {
        user = null;
    }

    public boolean doLoan(String answer) {

        boolean isCorrect = false;

        if (answer.equals("1")) {

            isCorrect = createLoan(100_000, 10_000_000, 12, 120, 9);

        } else if (answer.equals("2")) {

            isCorrect = createLoan(1_000_000, 5_000_000, 12, 240, 6);

        } else if (answer.equals("3")) {

            isCorrect = createLoan(1_000, 50_000, 1, 5, 25);

        } else if (answer.equals("4")) {
            return true;
        } else {
            System.out.println("Incorrect value");
        }

        return isCorrect;
    }

    public boolean createLoan(double minSum, double maxSum, int minTerm, int maxTerm, int interestRate) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        int term = 0;
        double sumPerMonth = 0;
        double totalSum = 0;
        boolean agree = false;

        try {
            System.out.println("Enter the amount of the desired loan (Min = " + minSum + ", Max = " + maxSum + ")");
            sum = Integer.parseInt(rd.readLine());
            System.out.println("Enter the time in months of the desired loan (Min = " + minTerm + ", Max = " + maxTerm + ")");
            term = Integer.parseInt(rd.readLine());
        } catch (IOException e) {
            System.out.println("Error");
        }

        if (maxSum < sum && minSum > sum) {
            System.out.println("You have entered an invalid amount. Read the terms of the loan again");
            return false;
        }
        if (maxTerm < term && minTerm > term) {
            System.out.println("You have entered an invalid credit term. Read the terms of the loan again");
            return false;
        }

        totalSum = calculateCreditSum(sum, term, interestRate);
        sumPerMonth = calculateSumPerMonth(totalSum, term);

        agree = bankMenu.showCurrentLoanTerms(sum, term, interestRate, sumPerMonth, totalSum);

        if (agree == false) {
            return false;
        }

        user.getLoanList().add(new Loan(new Date(), totalSum, interestRate, sumPerMonth, term));
        user.setBalance(user.getBalance() + sum);
        serializeUsers(users);

        return true;
    }

    public double calculateSumPerMonth(double totalSum, int term) {

        return totalSum / term;
    }

    public double calculateCreditSum(double sum, int term, int interestRate) {

        double dInterestRate = interestRate;
        sum = sum * (1 + (dInterestRate / 100));

        while (term > 12) {
            term -= 12;
            sum = sum * (1 + (dInterestRate / 100));
        }

        return sum;
    }

    public void doCreditCard() {
        String num = createCardNumber();
        int cvv = createCVV();
        user.getDebitCardList().add(new DebitCard(num, cvv));
        serializeUsers(users);
    }

    public String createCardNumber() {
        Random rnd = new Random();
        int part = 0;
        String num = "";

        while (true) {
            for (int i = 0; i < 4; i++) {
                part = rnd.nextInt(1111,9999);
                num += part + " ";
            }
            if (!cardNumbers.contains(num)) {
                cardNumbers.add(num);
                break;
            }
        }

        return num;

    }

    public int createCVV() {
        Random rnd = new Random();
        int cvv = rnd.nextInt(111,999);

        return cvv;
    }

}
