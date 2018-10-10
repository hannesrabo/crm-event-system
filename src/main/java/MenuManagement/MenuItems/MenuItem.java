package MenuManagement.MenuItems;

import UserManagement.UserRole;

import java.util.ArrayList;

public abstract class MenuItem {
    private ArrayList<UserRole> authorizedRoles = new ArrayList<UserRole>();

    public abstract String GetMenuItemName();
    public abstract void RunMenuItemFunction();

    public boolean isRoleAuthorized(UserRole role) {
        return authorizedRoles.contains(role);
    }

    protected void addAuthorizedRole(UserRole role) {
        authorizedRoles.add(role);
    }
}
