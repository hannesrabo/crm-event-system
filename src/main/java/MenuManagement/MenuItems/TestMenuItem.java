package MenuManagement.MenuItems;

import UserManagement.UserRole;

import java.util.ArrayList;

public class TestMenuItem implements MenuItem {
    private ArrayList<UserRole> roles;
    public boolean testHasRan = false;

    public TestMenuItem() {
        roles = new ArrayList<UserRole>();
        roles.add(UserRole.FinancialManager);
    }

    public String GetMenuItemName() {
        return "TestMenuItem";
    }

    public void RunMenuItemFunction() {
        System.out.println("Running test menu item"); testHasRan = true;
    }

    public boolean isRoleAuthorized(UserRole role) {
        return roles.contains(role);
    }
}
