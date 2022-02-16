import data.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final String dataFile = "Users.dat";

    private List<User> users;
    private User user;

    public User getUser() {
        return this.user;
    }

    public void start() {
        deserializeUsers();
        BankMenu bm = new BankMenu(this);
        bm.showStartMenu();
    }

    public void serializeUsers(List<User> users) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(dataFile));
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
        } catch (IOException e) {
            System.out.println("Access error");;
        } catch (ClassNotFoundException e) {
            System.out.println("Server error");
        }

    }

    public boolean doLogin(String email, String password) {
        boolean isCorrect = false;
        for (User user: users) {
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

    public void doRegister(User user) {
        users.add(user);
        serializeUsers(users);
    }

    public void userExit() {
        user = null;
    }

}
