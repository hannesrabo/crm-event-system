package UserManagement;


import java.util.ArrayList;

public class LoginManager {
    private final ArrayList<User> users;
    private User currentUser;

    public LoginManager() {
        users = new ArrayList<>(10);
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

    public void addUser(String username, String password, UserRole role) {
        User newUser = new User(username, role);
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

    public UserRole getUserRole() {
        return currentUser.role;
    }

    public User getUser() {
        return currentUser;
    }

    public String getUserListing() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (User user: users) {
            sb.append("\t").append(user.getName());
            sb.append(",\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    public User getUserFromName(String username) throws IllegalArgumentException{
        for (User u: users) {
            if (u.userName.equals(username)){
                return u;
            }
        }

        throw new IllegalArgumentException("User with username: " + username +" does not exist");
    }
}
