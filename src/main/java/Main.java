import EventManagement.*;
import FinancialRequestManagment.FinancialRequest;
import FinancialRequestManagment.FinancialRequestManager;
import FinancialRequestManagment.FinancialRequestStatus;
import MenuManagement.Menu;
import MenuManagement.MenuItems.*;
import RecruitmentManagement.RecruitmentManager;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.Department;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {

    private static void addUserLogins(LoginManager loginManager) {
        loginManager.addUser("cso1", "1234", UserRole.CustomerServiceOfficer);
        loginManager.addUser("cso2", "1234", UserRole.CustomerServiceOfficer);
        loginManager.addUser("scso", "1234", UserRole.SeniorCustomerOfficer);
        loginManager.addUser("finman", "1234", UserRole.FinancialManager);
        loginManager.addUser("admam", "1234", UserRole.AdministrationDepartmentManager);
        loginManager.addUser("sdmem1", "1234", UserRole.ServiceDepartmentMember);
        loginManager.addUser("sdmem2", "1234", UserRole.ServiceDepartmentMember);
        loginManager.addUser("sdmem3", "1234", UserRole.ServiceDepartmentMember);
        loginManager.addUser("pdman", "1234", UserRole.ProductionDepartmentManager);
        loginManager.addUser("pdmem1", "1234", UserRole.ProductionDepartmentMember);
        loginManager.addUser("pdmem2", "1234", UserRole.ProductionDepartmentMember);
        loginManager.addUser("pdmem3", "1234", UserRole.ProductionDepartmentMember);
        loginManager.addUser("sdman", "1234", UserRole.ServiceDepartmentManager);
        loginManager.addUser("hrmem", "1234", UserRole.HRStaffMember);
    }

    private static void addSystemItems(Menu mainMenu, LoginManager loginManager) {
        EventPlanManager eventPlanManager = new EventPlanManager();
        TaskManager taskManager = new TaskManager();
        FinancialRequestManager financialRequestManager = new FinancialRequestManager();
        RecruitmentManager recruitmentManager = new RecruitmentManager();

        // DEBUG
        EventPlan ep1 = new EventPlan()
                .setEventName("test Event")
                .setClient("test Client")
                .setEventType("WorkShop")
                .setDates(LocalDateTime.of(2018, 01, 01, 18, 00), LocalDateTime.of(2018, 01, 02, 18, 00))
                .setAttendees(10)
                .setBudget(1234)
                .setComment("AmazingStuffComment")
                .setStatus(EventStatus.Approved);
        eventPlanManager.add(ep1);

        EventPlan ep2 = new EventPlan()
                .setEventName("Other event")
                .setClient("KTH")
                .setEventType("Dinner")
                .setDates(LocalDateTime.of(2018, 01, 01, 18, 00), LocalDateTime.of(2018, 01, 02, 18, 00))
                .setAttendees(8)
                .setBudget(3000)
                .setComment("They need food")
                .setFinancialFeedback("The budget looks good")
                .setStatus(EventStatus.FinanceFeedbackGiven);
        eventPlanManager.add(ep2);

        Task t = new Task()
                .setName("TaskName")
                .setDescription("Description")
                .setPriority(TaskPriority.High)
                .assignEvent(ep1)
                .assignEmployee(loginManager.getUserFromName("sdmem1"))
                .addComment("Task Comment");
        taskManager.addTask(t);

        FinancialRequest fr1 = new FinancialRequest()
                .setDepartment(Department.Production)
                .setName("Music")
                .setProjectReference("Other event")
                .setReason("They need more music!")
                .setRequiredAmount(500)
                .setStatus(FinancialRequestStatus.New);

        FinancialRequest fr2 = new FinancialRequest()
                .setDepartment(Department.Production)
                .setName("Drinks")
                .setProjectReference("Other event")
                .setReason("They need more drinks!")
                .setRequiredAmount(200)
                .setStatus(FinancialRequestStatus.Approved);

        FinancialRequest fr3 = new FinancialRequest()
                .setDepartment(Department.Production)
                .setName("Balloons")
                .setProjectReference("test Event")
                .setReason("They need more Balloons!")
                .setRequiredAmount(5000)
                .setStatus(FinancialRequestStatus.Rejected);

        financialRequestManager.add(fr1);
        financialRequestManager.add(fr2);
        financialRequestManager.add(fr3);

        // General
        mainMenu.addMenuItem(new CreateEventMenuItem(eventPlanManager));
        mainMenu.addMenuItem(new ShowEventRequestsMenuItem(eventPlanManager, loginManager));
        mainMenu.addMenuItem(new ShowEventPlansMenuItem(eventPlanManager, loginManager, taskManager, recruitmentManager));
        mainMenu.addMenuItem(new ListUserTasks(taskManager, loginManager));
        mainMenu.addMenuItem(new ListRecruitmentRequests(loginManager, recruitmentManager));

        // Financial requests
        mainMenu.addMenuItem(new CreateFinancialRequestMenuItem(loginManager, financialRequestManager));
        mainMenu.addMenuItem(new ShowFinancialRequestsMenuItem(financialRequestManager, loginManager));

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
        addSystemItems(mainMenu, loginManager);


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
