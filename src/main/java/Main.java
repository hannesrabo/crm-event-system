import UserManagement.User;
import UserManagement.UserRole;

public class Main {

    public static void main(String args[]) {
        User u = new User("hej");
        u.addUserRole(UserRole.FinancialManager);

        System.out.println(u);
    }
}
