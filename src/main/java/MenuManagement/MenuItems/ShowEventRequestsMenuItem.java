package MenuManagement.MenuItems;

import EventManagement.EventPlan;
import EventManagement.EventPlanManager;
import EventManagement.EventStatus;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.InputReader;

import java.util.ArrayList;

public class ShowEventRequestsMenuItem extends MenuItem {
    private final EventPlanManager eventPlanManager;
    private final LoginManager loginManager;

    public ShowEventRequestsMenuItem(EventPlanManager eventPlanManager, LoginManager loginManager) {
        this.eventPlanManager = eventPlanManager;
        this.loginManager = loginManager;

        addAuthorizedRole(UserRole.SeniorCustomerOfficer);
        addAuthorizedRole(UserRole.CustomerServiceOfficer);
        addAuthorizedRole(UserRole.FinancialManager);
        addAuthorizedRole(UserRole.AdministrationDepartmentManager);
    }

    @Override
    public String GetMenuItemName() {
        return "Show event requests";
    }

    @Override
    public void RunMenuItemFunction() {

        ArrayList<EventPlan> eventPlans = eventPlanManager.getEventPlans(loginManager.getUserRole());

        if (eventPlans.size() < 1) {
            System.out.println("No events to show.");
            return;
        }

        System.out.println(EventPlanManager.createEventPlanListing(eventPlans));

        try {
            int index = Integer.parseInt(InputReader.readUserInput("Select Index"));

            if (index < 0 || index > eventPlans.size()) {
                System.out.println("Index does not exist");
                return;
            }

            // We use human indexing starting at 1 which means we have to subtract one here.
            EventPlan eventPlan = eventPlans.get(index - 1);

            System.out.println("\n" + eventPlan);
            editEventPlan(eventPlan);

        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid index.");
        }

    }

    private void editEventPlan(EventPlan eventPlan) {
        System.out.println("Edit Event Plan: Select Action\n-------------------\n");
        switch (loginManager.getUserRole()) {
            case CustomerServiceOfficer:
                System.out.println("(ENTER) Exit");
                InputReader.readUserInput("waiting");
                break;
            case SeniorCustomerOfficer:
                if (eventPlan.getStatus().equals(EventStatus.NewEventRequest)) {
                    System.out.println("(1) Approve");
                    System.out.println("(2) Exit");
                    try {
                        int choice = Integer.parseInt(InputReader.readUserInput("nr"));
                        if (choice != 1 && choice != 2)
                            throw new IllegalArgumentException();

                        if (choice == 1) {
                            eventPlan.setStatus(EventStatus.InitiallyAccepted);
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Not a valid number.");
                    }

                } else {
                    System.out.println("(ENTER) Exit");
                    InputReader.readUserInput("waiting");
                }
                break;
            case AdministrationDepartmentManager:
                System.out.println("(1) Approve");
                System.out.println("(2) Exit");
                try {
                    int choice = Integer.parseInt(InputReader.readUserInput(""));
                    if (choice != 1 && choice != 2)
                        throw new IllegalArgumentException();

                    if (choice == 1) {
                        eventPlan.setStatus(EventStatus.Approved);
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Not a valid number.");
                }
                break;
            case FinancialManager:
                System.out.println("(1) Give Financial Feedback");
                System.out.println("(2) Exit");
                try {
                    int choice = Integer.parseInt(InputReader.readUserInput(""));
                    if (choice != 1 && choice != 2)
                        throw new IllegalArgumentException();

                    if (choice == 1) {
                        eventPlan.setFinancialFeedback(InputReader.readUserInput("Financial Feedback"));
                        eventPlan.setStatus(EventStatus.FinanceFeedbackGiven);
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Not a valid number.");
                }
                break;
        }
    }
}
