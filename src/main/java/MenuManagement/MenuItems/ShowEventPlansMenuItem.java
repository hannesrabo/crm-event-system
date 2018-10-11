package MenuManagement.MenuItems;

import EventManagement.*;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.InputReader;

import java.util.ArrayList;

import static Utils.InputReader.readUserInput;

public class ShowEventPlansMenuItem extends MenuItem {

    private final EventPlanManager eventPlanManager;
    private final LoginManager loginManager;
    private final TaskManager taskManager;

    public ShowEventPlansMenuItem(EventPlanManager eventPlanManager, LoginManager loginManager, TaskManager taskManager) {
        this.eventPlanManager = eventPlanManager;
        this.loginManager = loginManager;
        this.taskManager = taskManager;

        addAuthorizedRole(UserRole.ProductionDepartmentManager);
        addAuthorizedRole(UserRole.ServiceDepartmentManager);
    }

    @Override
    public String GetMenuItemName() {
        return "Show Active Event Plans";
    }

    @Override
    public void RunMenuItemFunction() {

        ArrayList<EventPlan> eventPlans = eventPlanManager.getEventPlans(EventStatus.Approved);

        if (eventPlans.size() < 1) {
            System.out.println("No events to show.");
            return;
        }

        System.out.println(EventPlanManager.createEventPlanListing(eventPlans));

        try {
            int index = Integer.parseInt(readUserInput("Select Index"));

            if (index < 0 || index > eventPlans.size()) {
                System.out.println("Index does not exist");
                return;
            }

            // We use human indexing starting at 1 which means we have to subtract one here.
            EventPlan selectedEventPlan = eventPlans.get(index - 1);

            // Show
            System.out.println("\n" + selectedEventPlan);

            // Menu
            updateEventPlan(selectedEventPlan);

        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid index.");
        }


    }

    private void updateEventPlan(EventPlan eventPlan) {
        System.out.println("(1) Create new task");
        System.out.println("(2) List all tasks");
        System.out.println("(3) Exit");
        try {
            int choice = Integer.parseInt(readUserInput(""));
            if (choice != 1 && choice != 2 && choice != 3)
                throw new IllegalArgumentException();

            if (choice == 1) {
                System.out.println("Create new task:\n--------------------");
                taskManager.addTask(new Task()
                        .setName(readUserInput("Name"))
                        .setDescription(readUserInput("Description"))
                        .setPriority(TaskPriority.valueOf(InputReader.readUserInput("Priority (High, Medium, Low)")))
                        .assignEmployee(
                                loginManager.getUserFromName(
                                        InputReader.readUserInput("Username: " + loginManager.getUserListing())
                                )
                            )
                        .assignEvent(eventPlan)
                        );
            } else if (choice == 2) {
                if (taskManager.getEventTasks(eventPlan).size() < 1) {
                    System.out.println("No tasks to show.");
                } else {
                    System.out.println(taskManager.getEventTaskListing(eventPlan));
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid input. Try again");
        }
    }
}
