package EventManagement;

import UserManagement.User;

import java.util.ArrayList;
import java.util.Collection;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task t) {
        tasks.add(t);
    }

    public ArrayList<Task> getUserTasks(User user) {
        ArrayList<Task> result = new ArrayList<Task>();

        for (Task t : tasks) {
            if (t.getUser().equals(user))
                result.add(t);
        }

        return result;
    }

    public ArrayList<Task> getEventTasks(EventPlan eventPlan) {
        ArrayList<Task> result = new ArrayList<Task>();

        for (Task t : tasks) {
            if (t.getEvent().equals(eventPlan))
                result.add(t);
        }

        return result;
    }
}
