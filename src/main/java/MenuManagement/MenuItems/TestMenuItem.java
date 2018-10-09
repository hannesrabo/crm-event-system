package MenuManagement.MenuItems;

public class TestMenuItem implements MenuItem {
    public String GetMenuItemName() {
        return "TestMenuItem";
    }

    public void RunMenuItemFunction() {
        System.out.println("Running test menu item");
    }
}
