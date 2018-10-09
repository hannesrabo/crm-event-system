package MenuManagement;

import MenuManagement.MenuItems.MenuItem;
import UserManagement.LoginManager;

import java.util.LinkedList;

public class Menu {

    private LinkedList<MenuItem> menuItems;
    public static String MENU_HEADER = "Menu Items: \n------------------------\n";
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
}
