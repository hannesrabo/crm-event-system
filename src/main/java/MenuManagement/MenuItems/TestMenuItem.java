package MenuManagement.MenuItems;

import UserManagement.UserRole;

import java.util.ArrayList;

public class TestMenuItem implements MenuItem {
    private ArrayList<UserRole> roles;

    public TestMenuItem() {
        roles = new ArrayList<UserRole>();
        roles.add(UserRole.FinancialManager);
    }

    public String GetMenuItemName() {
        return "TestMenuItem";
    }

    public void RunMenuItemFunction() {
        System.out.println("Running test menu item");
    }

    public boolean isRoleAuthorized(UserRole role) {
        return roles.contains(role);
    }
}
