import MenuManagement.Menu;
import MenuManagement.MenuItems.LogoutMenuItem;
import MenuManagement.MenuItems.TestMenuItem;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import org.junit.Test;

import static MenuManagement.Menu.MENU_HEADER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MenuTest {

    private LoginManager createLoginManager () {
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
    public void TestPrintMenuItems() {
        LoginManager lm = createLoginManager();
        Menu m = new Menu(lm);
        String s = m.toString();

        assertEquals(MENU_HEADER, s);
    }

    @Test
    public void TestMenuItemRoles() {
        TestMenuItem item = new TestMenuItem();
        assertFalse(item.isRoleAuthorized(UserRole.ProductionDepartmentManager));
        assertTrue(item.isRoleAuthorized(UserRole.FinancialManager));
    }

    @Test
    public void TestRoles() {
        LoginManager lm = createLoginManager();
        TestMenuItem item = new TestMenuItem();

        Menu m = new Menu(lm);
        m.addMenuItem(item);

        assertEquals(MENU_HEADER, m.toString());

        lm.logout();
        lm.login("jcelik", "5678");
        assertEquals(MENU_HEADER + "(1): " + item.GetMenuItemName() +"\n", m.toString());
    }

    @Test
    public void TestRunFunction() {
        LoginManager lm = createLoginManager();
        TestMenuItem item = new TestMenuItem();

        Menu m = new Menu(lm);
        m.addMenuItem(item);
        assertFalse(m.run(0));
        assertFalse(m.run(1));
        assertFalse(m.run(2));

        lm.logout();
        lm.login("jcelik", "5678");

        assertTrue(m.run(1));
        assertTrue(item.testHasRan);
    }

    @Test
    public void TestLogoutMenuItem() {
        LoginManager lm = createLoginManager();
        LogoutMenuItem mi = new LogoutMenuItem(lm);

        Menu m = new Menu(lm);
        m.addMenuItem(mi);
        assertTrue(lm.userAuthenticated());

        m.run(1);
        assertFalse(lm.userAuthenticated());
    }
}
