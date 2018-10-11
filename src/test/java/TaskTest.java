import EventManagement.*;
import UserManagement.User;
import UserManagement.UserRole;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaskTest {
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
    public void TestCreationOfTasks() {

        EventPlan ep = getNew();
        EventPlan ep2 = getNew();
        TaskManager tm = new TaskManager();

        User u1 = new User("MyName", UserRole.FinancialManager);
        User u2 = new User("MySecondName", UserRole.ProductionDepartmentManager);

        Task t = new Task()
                .setName("TaskName")
                .setDescription("Description")
                .setPriority(TaskPriority.High)
                .assignEvent(ep)
                .assignEmployee(u1)
                .addComment("Task Comment");

        String s = t.toString();
        assertEquals(s,"Task: TaskName\n" +
                "---------------------\n" +
                "Description: Description\n" +
                "Priority: High\n" +
                "Event: testEvent\n" +
                "Employee: MyName\n" +
                "Comment: Task Comment\n"
                );

        tm.addTask(t);

        assertTrue(tm.getUserTasks(u1).size() > 0);
        assertFalse(tm.getUserTasks(u2).size() > 0);

        assertTrue(tm.getEventTasks(ep).size() > 0);
        assertFalse(tm.getEventTasks(ep2).size() > 0);
    }
}
