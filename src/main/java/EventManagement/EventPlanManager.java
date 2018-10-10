package EventManagement;

import UserManagement.UserRole;

import java.util.ArrayList;

public class EventPlanManager {
    private final ArrayList<EventPlan> eventPlans = new ArrayList<EventPlan>();

    public int add(EventPlan eventPlan) {
        eventPlans.add(eventPlan);
        return eventPlans.size() - 1;
    }

    public EventPlan getEventPlan(int i) {
        return eventPlans.get(i);
    }

    public Iterable<? extends EventPlan> getEventPlans() {
        return eventPlans;
    }

    public int numberOfEvents() {
        return eventPlans.size();
    }

    private ArrayList<EventPlan> getEventPlans(EventStatus status) {
        ArrayList<EventPlan> result = new ArrayList<>();
        for(EventPlan e : eventPlans) {
            if (e.getStatus().equals(status))
                result.add(e);
        }

        return result;
    }

    public ArrayList<EventPlan> getEventPlans(UserRole role) {
        switch (role) {
            case CustomerServiceOfficer:
                return getEventPlans(EventStatus.NewEventRequest);
            case SeniorCustomerOffice:
                ArrayList<EventPlan> result = getEventPlans(EventStatus.NewEventRequest);
                result.addAll(getEventPlans(EventStatus.Approved));
                return result;
            case FinancialManager:
                return getEventPlans(EventStatus.InitiallyAccepted);
            case AdministrationDepartmentManager:
                return getEventPlans(EventStatus.FinanceFeedbackGiven);
        }

        return new ArrayList<>();
    }
}
