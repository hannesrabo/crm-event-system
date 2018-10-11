package EventManagement;

import UserManagement.User;

public class Task {
    private String description = "";
    private TaskPriority priority = TaskPriority.Medium;
    private EventPlan eventPlan = null;
    private User user = null;
    private String taskName = "";
    private String comment = "";

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public Task setPriority(TaskPriority priority) {
        this.priority = priority;
        return this;
    }

    public Task assignEvent(EventPlan eventPlan) {
        this.eventPlan = eventPlan;
        return this;
    }

    public Task assignEmployee(User user) {
        this.user = user;
        return this;
    }

    public User getUser() {
        return user;
    }

    public EventPlan getEvent() {
        return eventPlan;
    }

    public Task setName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    @Override
    public String toString() {
        return String.join("",
                "Task: ", taskName,
                    "\n---------------------",
                    "\nDescription: ", description,
                    "\nPriority: ", priority.toString(),
                    "\nEvent: ", eventPlan.getName(),
                    "\nEmployee: ", user.getName(),
                    "\nComment: ", comment,
                    "\n"
                );

    }

    public Task addComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getName() {
        return taskName;
    }
}
