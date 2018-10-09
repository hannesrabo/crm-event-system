import EventManagement.EventPlan;
import EventManagement.EventPlanManager;
import EventManagement.EventPlanType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventPlanTest {

    @Test
    public void CreateEventPlanTest() {
        EventPlan ep = new EventPlan()
                .setEventName("testEvent")
                .setClient("testClient")
                .setEventType(EventPlanType.WorkShop)
                .setDates(LocalDateTime.of(2018, 01, 01, 18, 00), LocalDateTime.of(2018, 01, 02, 18, 00))
                .setAttendees(10)
                .setBudget(1234)
                .setComment("AmazingStuffComment");

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
}
