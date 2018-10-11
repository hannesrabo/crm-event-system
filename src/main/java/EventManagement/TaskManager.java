package EventManagement;

import UserManagement.User;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task t) {
        tasks.add(t);
    }

    public ArrayList<Task> getUserTasks(User user) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getUser().equals(user))
                result.add(t);
        }

        return result;
    }

    public ArrayList<Task> getEventTasks(EventPlan eventPlan) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getEvent().equals(eventPlan))
                result.add(t);
        }

        return result;
    }

    public String getEventTaskListing(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int index = 1;

        for (Task task : tasks) {
            sb.append("(");
            sb.append(index);
            sb.append("): ");
            sb.append(task.getName());
            sb.append(" [");
            sb.append(task.getStatus());
            sb.append("] - ");
            sb.append(" [");
            sb.append(task.getPriority());
            sb.append("]\n");
            index++;
        }

        return sb.toString();
    }

    public String getEventTaskListing(EventPlan eventPlan) {
        return getEventTaskListing(getEventTasks(eventPlan));
    }
}
