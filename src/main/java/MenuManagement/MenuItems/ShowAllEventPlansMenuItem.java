package MenuManagement.MenuItems;

import EventManagement.EventPlan;
import EventManagement.EventPlanManager;
import UserManagement.UserRole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowAllEventPlansMenuItem implements MenuItem {
    private final EventPlanManager eventPlanManager;

    public ShowAllEventPlansMenuItem(EventPlanManager eventPlanManager) {
        this.eventPlanManager = eventPlanManager;
    }

    @Override
    public String GetMenuItemName() {
        return "Show all event plans";
    }

    @Override
    public void RunMenuItemFunction() {
        if(eventPlanManager.numberOfEvents() < 1) {
            System.out.println("No events to show.\n");
            return;
        }

        System.out.println("Event Plans: \n--------------------");
        int index = 1;
        for (EventPlan e : eventPlanManager.getEventPlans()) {
            System.out.println("(" +index+"): " + e.getName());
        }

        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        try {
            System.out.print("> ");
            index = Integer.parseInt(bufferedReader.readLine());

            if (index < 0 || index > eventPlanManager.numberOfEvents()) {
                System.out.println("Index does not exist");
                return;
            }

            // We use human indexing starting at 1 which means we have to subtract one here.
            System.out.println("\n" + eventPlanManager.getEventPlan(index - 1));

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Not a valid index.");
        }

    }

    @Override
    public boolean isRoleAuthorized(UserRole role) {
        return role.equals(UserRole.SeniorCustomerOffice);
    }
}
