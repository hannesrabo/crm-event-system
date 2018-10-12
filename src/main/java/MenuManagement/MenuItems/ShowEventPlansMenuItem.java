package MenuManagement.MenuItems;

import EventManagement.*;
import RecruitmentManagement.RecruitmentEmploymentForm;
import RecruitmentManagement.RecruitmentManager;
import RecruitmentManagement.RecruitmentRequest;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.DepartmentUtils;
import Utils.InputReader;

import java.util.ArrayList;

import static Utils.InputReader.readUserInput;

public class ShowEventPlansMenuItem extends MenuItem {

    private final EventPlanManager eventPlanManager;
    private final LoginManager loginManager;
    private final TaskManager taskManager;
    private final RecruitmentManager recruitmentManager;

    public ShowEventPlansMenuItem(EventPlanManager eventPlanManager, LoginManager loginManager, TaskManager taskManager, RecruitmentManager recruitmentManager) {
        this.eventPlanManager = eventPlanManager;
        this.loginManager = loginManager;
        this.taskManager = taskManager;
        this.recruitmentManager = recruitmentManager;

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

        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("Not a valid index");
        }


    }

    private void updateEventPlan(EventPlan eventPlan) {
        System.out.println("(1) Create new task");
        System.out.println("(2) List all tasks");
        System.out.println("(3) Create new recruitment request");
        System.out.println("(4) Exit");
        try {
            int choice = Integer.parseInt(readUserInput(""));
            if (choice < 1 || choice > 4)
                throw new IllegalArgumentException();

            switch (choice) {
                case 1: // Create task
                    ArrayList<UserRole> roles = new ArrayList<>();
                    roles.add(UserRole.ProductionDepartmentMember);
                    roles.add(UserRole.ServiceDepartmentMember);

                    System.out.println("Create new task:\n--------------------");
                    taskManager.addTask(new Task()
                            .setName(readUserInput("Name"))
                            .setDescription(readUserInput("Description"))
                            .setPriority(TaskPriority.valueOf(InputReader.readUserInput("Priority (High, Medium, Low)")))
                            .assignEmployee(
                                    loginManager.getUserFromName(
                                            InputReader.readUserInput("Username: " + loginManager.getUserListing(roles))
                                    )
                            )
                    );
                    // Re-run as we may want to add more tasks
                    updateEventPlan(eventPlan);
                    break;
                case 2: // List tasks
                    if (taskManager.getEventTasks(eventPlan).size() < 1) {
                        System.out.println("No tasks to show.");
                    } else {
                        System.out.println(taskManager.getEventTaskListing(eventPlan));
                    }
                    break;
                case 3: // Create recruitment request
                    System.out.println("Creating New Recruitment Request:\n------------------");
                    recruitmentManager.addNewRecruitmentRequest(
                            new RecruitmentRequest()
                                .setTitle(InputReader.readUserInput("Title"))
                                .setRequestingDeparment(DepartmentUtils.roleToDepartment(loginManager.getUserRole()))
                                .setUser(loginManager.getUser())
                                .setEventPlan(eventPlan)
                                .setEmploymentForm(
                                        RecruitmentEmploymentForm.valueOf(InputReader.readUserInput(
                                                "Recruitment Form: " + RecruitmentEmploymentForm.createListing()
                                        ))
                                )
                                .setRequiredExperienceYears(
                                        Integer.parseInt(InputReader.readUserInput("Required experience (years)"))
                                )
                                .setDescription(InputReader.readUserInput("Description"))
                    );
                    break;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid input");
        }
    }
}
