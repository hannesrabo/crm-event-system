package UserManagement;

import java.util.LinkedList;

public class User {
    String userName;
    String password;
    public UserRole role;

    public User(String userName, UserRole role) {
        this.userName = userName;
        this.role = role;
    }

    @Override
    public String toString() {
        return userName + ": " + role;
    }
}
