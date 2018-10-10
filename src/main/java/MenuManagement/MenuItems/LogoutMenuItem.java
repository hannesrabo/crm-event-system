package MenuManagement.MenuItems;

import UserManagement.LoginManager;
import UserManagement.UserRole;

public class LogoutMenuItem extends MenuItem {
    private final LoginManager loginManager;

    public LogoutMenuItem(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public String GetMenuItemName() {
        return "Logout";
    }

    @Override
    public void RunMenuItemFunction() {
        loginManager.logout();
    }

    @Override
    public boolean isRoleAuthorized(UserRole role) {
        return true;
    }
}
