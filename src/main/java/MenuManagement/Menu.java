package MenuManagement;

import MenuManagement.MenuItems.MenuItem;

import java.util.LinkedList;

public class Menu {

    private LinkedList<MenuItem> menuItems;
    public static String MENU_HEADER = "Menu Items: \n------------------------\n";

    public Menu() {
        menuItems = new LinkedList<MenuItem>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(MENU_HEADER);

        int index = 1;
        for (MenuItem item : menuItems) {
            sb.append("(");
            sb.append(index);
            sb.append("): ");
            sb.append(item.GetMenuItemName());
            sb.append("\n");
        }

        return sb.toString();
    }

    public void addMenuItem(MenuItem testMenuItem) {
        menuItems.add(testMenuItem);
    }
}
