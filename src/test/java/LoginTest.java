import UserManagement.LoginManager;
import UserManagement.User;
import UserManagement.UserRole;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class LoginTest {

    public static LoginManager createLoginManager() {
        LoginManager lm = new LoginManager();
        String username = "hrabo";
        String password = "1234";
        UserRole role = UserRole.ProductionDepartmentManager;

        lm.addUser(username, password, role);
        lm.login(username, password);

        username = "jcelik";
        password = "5678";

        lm.addUser(username, password, UserRole.FinancialManager);

        return lm;
    }

    @Test
    public void TestUserHasRole() {
        User u1 = new User("MyName", UserRole.FinancialManager);

        assertEquals(u1.role, UserRole.FinancialManager);
        assertNotEquals(u1.role, UserRole.ProductionDepartmentMember);

        User u2 = new User("", UserRole.ProductionDepartmentManager);

        assertNotEquals(u2.role, UserRole.FinancialManager);
        assertEquals(u2.role, UserRole.ProductionDepartmentManager);

    }


    @Test
    public void TestUserCanLogInAndOut () {
        LoginManager lm = new LoginManager();

        String username = "hrabo";
        String password = "1234";
        UserRole role = UserRole.ProductionDepartmentManager;

        lm.addUser(username, password, role);
        assertTrue(lm.hasUser(username));

        assertTrue(lm.login(username, password));
        assertTrue(lm.userAuthenticated());
        assertEquals(lm.getUserRole(), role);

        lm.logout();
        assertFalse(lm.userAuthenticated());
    }
}
