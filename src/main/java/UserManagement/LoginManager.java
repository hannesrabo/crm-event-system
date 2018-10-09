package UserManagement;


import java.util.ArrayList;

public class LoginManager {
    private final ArrayList<User> users;
    private User currentUser;

    public LoginManager() {
        users = new ArrayList<User>(10);
        currentUser = null;
    }

    public boolean login(String username, String password) {
        for (User u: users) {
            if (u.userName.equals(username) && u.password.equals(password)){
                currentUser = u;
                return true;
            }
        }

        return false;
    }

    public boolean userAuthenticated() {
        return currentUser != null;
    }

    public void addUser(String username, String password) {
        User newUser = new User(username);
        newUser.password = password;
        users.add(newUser);
    }

    public boolean hasUser(String username) {
        for (User u: users) {
            if (u.userName.equals(username)){
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }
}
