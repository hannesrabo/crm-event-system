package MenuManagement.MenuItems;

import EventManagement.Task;
import EventManagement.TaskManager;
import UserManagement.LoginManager;
import UserManagement.UserRole;

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
        int index = 1;
        ArrayList<Task> tasks = taskManager.getUserTasks(loginManager.getUser());

        if (tasks.size() < 1) {
            System.out.println("No tasks.");
            return;
        }

        System.out.println("My Tasks\n---------------------");
        for (Task task : tasks) {
            System.out.println("("+index+") " + task.getName());
            index++;
        }
        System.out.println();
    }
}
