package MenuManagement.MenuItems;

import UserManagement.UserRole;

public class ExitMenuItem extends MenuItem {
    public String GetMenuItemName() {
        return "Exit Program";
    }

    public void RunMenuItemFunction() {
        System.exit(0);
    }

    @Override
    public boolean isRoleAuthorized(UserRole role) { return true; }
}
