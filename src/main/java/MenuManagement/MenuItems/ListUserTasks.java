package MenuManagement.MenuItems;

import EventManagement.Task;
import EventManagement.TaskManager;
import EventManagement.TaskStatus;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.InputReader;

import java.util.ArrayList;

public class ListUserTasks extends MenuItem {

    private final TaskManager taskManager;
    private final LoginManager loginManager;

    public ListUserTasks(TaskManager taskManager, LoginManager loginManager) {
        this.taskManager = taskManager;
        this.loginManager = loginManager;

        addAuthorizedRole(UserRole.ServiceDepartmentMember);
        addAuthorizedRole(UserRole.ProductionDepartmentMember);
    }

    @Override
    public String GetMenuItemName() {
        return "List Tasks";
    }

    @Override
    public void RunMenuItemFunction() {
        ArrayList<Task> tasks = taskManager.getUserTasks(loginManager.getUser());

        if (tasks.size() < 1) {
            System.out.println("No tasks.");
            return;
        }

        System.out.println("My Tasks\n---------------------");
        System.out.println(taskManager.getEventTaskListing(tasks));

        try {
            int choice = Integer.parseInt(InputReader.readUserInput("Select Task for Editing:"));
            if (choice < 1 || choice > tasks.size())
                throw new IllegalArgumentException("Invalid range");

            Task task = tasks.get(choice - 1);
            System.out.println(task);
            System.out.println("Actions:\n-----------------");
            System.out.println("(1) Set Status");
            System.out.println("(2) Comment");
            System.out.println("(3) Exit");

            choice = Integer.parseInt(InputReader.readUserInput("Select Action:"));
            if (choice < 1 || choice > 3)
                throw new IllegalArgumentException("Invalid range");

            switch (choice) {
                case 1:
                    task.setStatus(
                            TaskStatus.valueOf(
                                    InputReader.readUserInput("New Status ("+ TaskStatus.valueListing() +")")
                            )
                    );
                    break;
                case 2:
                    task.addComment(
                            InputReader.readUserInput("Comment")
                    );
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid choice");
        }

    }
}
