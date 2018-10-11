package MenuManagement.MenuItems;

import UserManagement.UserRole;

public class TestMenuItem extends MenuItem {
    public boolean testHasRan = false;

    public TestMenuItem() {
        addAuthorizedRole(UserRole.FinancialManager);
    }

    public String GetMenuItemName() {
        return "TestMenuItem";
    }

    public void RunMenuItemFunction() {
        System.out.println("Running test menu item"); testHasRan = true;
    }
}
