package UserManagement;

import java.util.LinkedList;

public class User {
    String userName;
    String password;

    private LinkedList<UserRole> roles;

    public User(String userName) {
        this.userName = userName;

        roles = new LinkedList<UserRole>();
    }

    public void addUserRole(UserRole role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return userName + ": " + roles;
    }

    public boolean hasUserRole(UserRole role) {
        return roles.contains(role);
    }
}
