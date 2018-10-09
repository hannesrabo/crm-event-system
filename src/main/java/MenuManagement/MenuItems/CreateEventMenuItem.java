package MenuManagement.MenuItems;

import EventManagement.EventPlan;
import EventManagement.EventPlanManager;
import EventManagement.EventPlanType;
import UserManagement.UserRole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateEventMenuItem implements MenuItem {
    private final EventPlanManager eventPlanManager;

    public CreateEventMenuItem(EventPlanManager eventPlanManager) {
        this.eventPlanManager = eventPlanManager;
    }

    @Override
    public String GetMenuItemName() {
        return "Create new event";
    }

    @Override
    public void RunMenuItemFunction() {

        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        EventPlan ep = new EventPlan();
        String input;

        System.out.println("Create Event: \n" +
                "------------------------");

            try {
                System.out.print("Event name: ");
                ep.setEventName(bufferedReader.readLine());

                System.out.print("Client Name: ");
                ep.setClient(bufferedReader.readLine());

                System.out.print("Event Type: ");
                ep.setEventType(EventPlanType.getEnumFromString(bufferedReader.readLine()));

//                .setDates(LocalDateTime.of(2018, 01, 01, 18, 00), LocalDateTime.of(2018, 01, 02, 18, 00))

                System.out.print("Attendees: ");
                ep.setAttendees(Integer.parseInt(bufferedReader.readLine()));

                System.out.print("Budget: ");
                ep.setBudget(Integer.parseInt(bufferedReader.readLine()));

                System.out.print("Comment: ");
                ep.setComment(bufferedReader.readLine());

            } catch (IOException e){
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Number.");
            }

        eventPlanManager.add(ep);
    }

    @Override
    public boolean isRoleAuthorized(UserRole role) {
        return role.equals(UserRole.CustomerServiceOfficer);
    }
}
