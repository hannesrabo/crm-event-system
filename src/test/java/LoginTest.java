import UserManagement.LoginManager;
import UserManagement.User;
import UserManagement.UserRole;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class LoginTest {

    @Test
    public void TestUserHasRole() {
        User u1 = new User("MyName");
        u1.addUserRole(UserRole.FinancialManager);
        u1.addUserRole(UserRole.ProductionStaffMember);

        assertTrue(u1.hasUserRole(UserRole.FinancialManager));
        assertTrue(u1.hasUserRole(UserRole.ProductionStaffMember));
    }


    @Test
    public void TestUserCanLogInAndOut () {
        LoginManager lm = new LoginManager();

        String username = "hrabo";
        String password = "1234";

        lm.addUser(username, password);
        assertTrue(lm.hasUser(username));

        assertTrue(lm.login(username, password));
        assertTrue(lm.userAuthenticated());

        lm.logout();
        assertFalse(lm.userAuthenticated());
    }
}
