package MenuManagement.MenuItems;

import EventManagement.EventPlan;
import EventManagement.EventPlanManager;
import UserManagement.UserRole;
import Utils.InputReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
                .setEventType(InputReader.readUserInput("Event Type"))
                .setAttendees(Integer.parseInt(InputReader.readUserInput("Attendees")))
                .setBudget(Integer.parseInt(InputReader.readUserInput("Budget")))
                .setDates(
                        LocalDateTime.parse(InputReader.readUserInput("From: ("+EventPlan.dateTimeStringFormat+")"), EventPlan.dFormat),
                        LocalDateTime.parse(InputReader.readUserInput("To: ("+EventPlan.dateTimeStringFormat+")"), EventPlan.dFormat)
                )
                .setComment(InputReader.readUserInput("Comment"));

            eventPlanManager.add(ep);

        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("Invalid input");
        }
    }
}
