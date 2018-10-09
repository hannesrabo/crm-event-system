package MenuManagement;

import MenuManagement.MenuItems.MenuItem;
import UserManagement.LoginManager;

import java.util.LinkedList;

public class Menu {

    private LinkedList<MenuItem> menuItems;
    public static String MENU_HEADER = "=======================\nMenu Items: \n=======================\n";
    private LoginManager loginManager;

    public Menu(LoginManager loginManager) {
        menuItems = new LinkedList<MenuItem>();
        this.loginManager = loginManager;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(MENU_HEADER);

        int index = 1;
        for (MenuItem item : menuItems) {
            if (!item.isRoleAuthorized(loginManager.getUserRole()))
                continue;

            sb.append("(");
            sb.append(index);
            sb.append("): ");
            sb.append(item.GetMenuItemName());
            sb.append("\n");

            index++;
        }

        return sb.toString();
    }

    public void addMenuItem(MenuItem testMenuItem) {
        menuItems.add(testMenuItem);
    }

    public boolean run(int menuItemIndex) {
        int index = 1;
        for (MenuItem item : menuItems) {
            if (!item.isRoleAuthorized(loginManager.getUserRole()))
                continue;

            if (index == menuItemIndex) {
                item.RunMenuItemFunction();
                return true;
            } else if (index >= menuItemIndex) {
                return false;
            }

            index++;
        }

        return false;
    }
}
