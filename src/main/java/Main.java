import EventManagement.EventPlanManager;
import EventManagement.TaskManager;
import FinancialRequestManagment.FinancialRequestManager;
import MenuManagement.Menu;
import MenuManagement.MenuItems.*;
import UserManagement.LoginManager;
import UserManagement.UserRole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static void addUserLogins(LoginManager loginManager) {
        loginManager.addUser("cso", "1234", UserRole.CustomerServiceOfficer);
        loginManager.addUser("sco", "1234", UserRole.SeniorCustomerOfficer);
        loginManager.addUser("fin", "1234", UserRole.FinancialManager);
        loginManager.addUser("adm", "1234", UserRole.AdministrationDepartmentManager);
        loginManager.addUser("pdm", "1234", UserRole.ProductionManager);
        loginManager.addUser("sdm", "1234", UserRole.ServiceDepartmentMember);
    }

    private static void addMenuItems(Menu mainMenu, LoginManager loginManager) {
        EventPlanManager eventPlanManager = new EventPlanManager();
        FinancialRequestManager financialRequestManager = new FinancialRequestManager();

        // Event requests
        mainMenu.addMenuItem(new CreateEventMenuItem(eventPlanManager));
        mainMenu.addMenuItem(new ShowAllEventPlansMenuItem(eventPlanManager, loginManager));
        mainMenu.addMenuItem(new ListUserTasks(new TaskManager(), loginManager));

        // Financial requests
        mainMenu.addMenuItem(new CreateFinancialRequestMenuItem(loginManager, financialRequestManager));

        // Login and exit menu items.
        mainMenu.addMenuItem(new LogoutMenuItem(loginManager));
        mainMenu.addMenuItem(new ExitMenuItem());
    }

    public static void main(String args[]) {
        // Create readers
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        boolean active = true;

        // Create the login manager
        LoginManager loginManager = new LoginManager();
        addUserLogins(loginManager);

        // Create the main menu
        Menu mainMenu = new Menu(loginManager);
        addMenuItems(mainMenu, loginManager);


        System.out.println("CRM Event System. Welcome!\n" +
                "-------------------------------\n");

        // Continue forever (the system will never exit)
        while (active) {
            try {
                // Make sure the user is logged in.
                while (!loginManager.userAuthenticated()) {
                    System.out.print("\nLogin\n" +
                                     "Username: ");

                    String username = bufferedReader.readLine();

                    // Reading password as invisible characters if possible.
                    char [] rawPassword;
                    if (System.console() != null)
                        rawPassword = System.console().readPassword("Password: ");
                    else {
                        System.out.println("Password: ");
                        rawPassword = bufferedReader.readLine().toCharArray();
                    }
                    String password = new String(rawPassword);

                    loginManager.login(username, password);
                }

                // Continuously print the menu
                while (loginManager.userAuthenticated()) {
                    try {
                        // Generate the menu and perform actions.
                        System.out.println(mainMenu.toString());
                        System.out.print("> ");
                        int menuIdex = Integer.parseInt(bufferedReader.readLine());
                        boolean success = mainMenu.run(menuIdex);

                        if (!success) {
                            System.out.println("Could not find that item.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number. Try again.");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
