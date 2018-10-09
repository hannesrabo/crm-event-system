import MenuManagement.Menu;
import MenuManagement.MenuItems.TestMenuItem;
import org.junit.Test;

import static MenuManagement.Menu.MENU_HEADER;
import static org.junit.Assert.assertEquals;

public class MenuTest {

    @Test
    public void TestPrintMenuItems() {
        Menu m = new Menu();
        String s = m.toString();

        assertEquals(MENU_HEADER, s);

        TestMenuItem item = new TestMenuItem();
        m.addMenuItem(item);
        assertEquals(MENU_HEADER + "(1): " + item.GetMenuItemName() +"\n", m.toString());
    }
}
