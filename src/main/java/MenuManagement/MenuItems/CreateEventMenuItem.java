package MenuManagement.MenuItems;

import EventManagement.EventPlan;
import EventManagement.EventPlanManager;
import EventManagement.EventPlanType;
import UserManagement.UserRole;
import Utils.InputReader;

public class CreateEventMenuItem extends MenuItem {
    private final EventPlanManager eventPlanManager;

    public CreateEventMenuItem(EventPlanManager eventPlanManager) {
        this.eventPlanManager = eventPlanManager;

        addAuthorizedRole(UserRole.CustomerServiceOfficer);
    }

    @Override
    public String GetMenuItemName() {
        return "Create new event";
    }

    @Override
    public void RunMenuItemFunction() {

        System.out.println("Create Event: \n" +
                "------------------------");

        try {
            EventPlan ep = new EventPlan()
                .setEventName(InputReader.readUserInput("Event name"))
                .setClient(InputReader.readUserInput("Client Name"))
                .setEventType(EventPlanType.getEnumFromString(InputReader.readUserInput("Event Type")))
                .setAttendees(Integer.parseInt(InputReader.readUserInput("Attendees")))
                .setBudget(Integer.parseInt(InputReader.readUserInput("Budget")))
                .setComment(InputReader.readUserInput("Comment"));
//                .setDates(LocalDateTime.of(2018, 01, 01, 18, 00), LocalDateTime.of(2018, 01, 02, 18, 00))

            eventPlanManager.add(ep);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Number.");
        }
    }
}
