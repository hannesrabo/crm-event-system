package EventManagement;

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
}
