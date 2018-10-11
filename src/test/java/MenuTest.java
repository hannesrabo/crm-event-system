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

    @Test
    public void TestPrintMenuItems() {
        LoginManager lm = LoginTest.createLoginManager();
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
        LoginManager lm = LoginTest.createLoginManager();
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
        LoginManager lm = LoginTest.createLoginManager();
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
        LoginManager lm = LoginTest.createLoginManager();
        LogoutMenuItem mi = new LogoutMenuItem(lm);

        Menu m = new Menu(lm);
        m.addMenuItem(mi);
        assertTrue(lm.userAuthenticated());

        m.run(1);
        assertFalse(lm.userAuthenticated());
    }
}
