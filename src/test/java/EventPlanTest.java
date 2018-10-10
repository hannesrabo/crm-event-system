import EventManagement.EventPlan;
import EventManagement.EventPlanManager;
import EventManagement.EventPlanType;
import EventManagement.EventStatus;
import UserManagement.UserRole;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventPlanTest {

    private EventPlan getNew() {
        return new EventPlan()
                .setEventName("testEvent")
                .setClient("testClient")
                .setEventType(EventPlanType.WorkShop)
                .setDates(LocalDateTime.of(2018, 01, 01, 18, 00), LocalDateTime.of(2018, 01, 02, 18, 00))
                .setAttendees(10)
                .setBudget(1234)
                .setComment("AmazingStuffComment");
    }

    @Test
    public void CreateEventPlanTest() {
        EventPlan ep = getNew();

        assertEquals(
                "EventPlan: testEvent\n" +
                        "--------------------\n" +
                        "Client: testClient\n" +
                        "Type: WorkShop\n" +
                        "Attendees: 10\n" +
                        "Budget: 1234\n"+
                        "From: 2018-01-01 18:00\n" +
                        "To: 2018-01-02 18:00\n" +
                        "Comment: AmazingStuffComment\n",
                ep.toString());

        EventPlanManager eventPlanManager = new EventPlanManager();
        int i = eventPlanManager.add(ep);
        assertTrue(eventPlanManager.getEventPlan(i).equals(ep));

    }

    @Test
    public void EventPlanTypeTest() {
        assertEquals(EventPlanType.WorkShop, EventPlanType.getEnumFromString("WorkShop"));
        assertEquals(EventPlanType.Unknown, EventPlanType.getEnumFromString("RandomValue"));
    }

    @Test
    public void EventStatusTest() {
        EventPlan ep = getNew();
        EventPlanManager eventPlanManager = new EventPlanManager();
        eventPlanManager.add(ep);

        ep.setStatus(EventStatus.NewEventRequest);
        assertTrue(eventPlanManager.getEventPlans(UserRole.CustomerServiceOfficer).iterator().hasNext());
        assertTrue(eventPlanManager.getEventPlans(UserRole.SeniorCustomerOffice).iterator().hasNext());
        assertFalse(eventPlanManager.getEventPlans(UserRole.FinancialManager).iterator().hasNext());

        ep.setStatus(EventStatus.InitiallyAccepted);
        assertFalse(eventPlanManager.getEventPlans(UserRole.CustomerServiceOfficer).iterator().hasNext());
        assertFalse(eventPlanManager.getEventPlans(UserRole.SeniorCustomerOffice).iterator().hasNext());
        assertTrue(eventPlanManager.getEventPlans(UserRole.FinancialManager).iterator().hasNext());

        ep.setStatus(EventStatus.FinanceFeedbackGiven);
        assertFalse(eventPlanManager.getEventPlans(UserRole.CustomerServiceOfficer).iterator().hasNext());
        assertFalse(eventPlanManager.getEventPlans(UserRole.SeniorCustomerOffice).iterator().hasNext());
        assertFalse(eventPlanManager.getEventPlans(UserRole.FinancialManager).iterator().hasNext());
        assertTrue(eventPlanManager.getEventPlans(UserRole.AdministrationDepartmentManager).iterator().hasNext());

        ep.setStatus(EventStatus.Approved);
        assertFalse(eventPlanManager.getEventPlans(UserRole.CustomerServiceOfficer).iterator().hasNext());
        assertTrue(eventPlanManager.getEventPlans(UserRole.SeniorCustomerOffice).iterator().hasNext());
        assertFalse(eventPlanManager.getEventPlans(UserRole.FinancialManager).iterator().hasNext());
        assertFalse(eventPlanManager.getEventPlans(UserRole.AdministrationDepartmentManager).iterator().hasNext());

    }
}
