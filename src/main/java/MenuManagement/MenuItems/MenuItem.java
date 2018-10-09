package MenuManagement.MenuItems;

import UserManagement.UserRole;

public interface MenuItem {
    String GetMenuItemName();
    void RunMenuItemFunction();
    boolean isRoleAuthorized(UserRole role);
}
