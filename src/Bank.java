import data.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {

    static final long serialVersionUID = 812943703942L;
    private final String dataFile = "Users.dat";

    private List<User> users;

    public List<User> getUserList() {
        return users;
    }

    public void setUserList(List<User> userList) {
        this.users = userList;
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
}
