package EventManagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EventPlan {
    private String eventName = "";
    private String clientName = "";
    private String eventPlanType = "";
    private LocalDateTime eventStart = LocalDateTime.now(), eventEnd = LocalDateTime.now();
    private int attendees = 0;
    private int budget = 0;
    private String comment = "";
    private EventStatus status = EventStatus.NewEventRequest;
    private String financialFeedback = "";

    public static String dateTimeStringFormat = "yyyy-MM-dd HH:mm";
    public static DateTimeFormatter dFormat = DateTimeFormatter.ofPattern(dateTimeStringFormat, Locale.ENGLISH);

    @Override
    public String toString() {
        return String.join("",
                "EventPlan: ",  eventName,
                "\n--------------------\n",
                "Client: ", clientName,
                "\nType: ", eventPlanType.toString(),
                "\nAttendees: ", Integer.toString(attendees),
                "\nBudget: ", Integer.toString(budget),
                "\nFrom: ", dFormat.format(eventStart),
                "\nTo: ", dFormat.format(eventEnd),
                "\nComment: ", comment,
                "\nFinancial feedback: ", financialFeedback,
                "\n");
    }

    public EventPlan setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventPlan setClient(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public EventPlan setEventType(String eventPlanType) {
        this.eventPlanType = eventPlanType;
        return this;
    }

    public EventPlan setDates(LocalDateTime from, LocalDateTime to) {
        eventStart = from;
        eventEnd = to;
        return this;
    }

    public EventPlan setAttendees(int attendees) {
        this.attendees = attendees;
        return this;
    }

    public EventPlan setBudget(int budget) {
        this.budget = budget;
        return this;
    }

    public EventPlan setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getName() {
        return eventName;
    }

    public EventPlan setStatus(EventStatus newStatus) {
        this.status = newStatus;
        return this;
    }

    public EventStatus getStatus() {
        return this.status;
    }

    public EventPlan setFinancialFeedback(String financialFeedback) {
        this.financialFeedback = financialFeedback;
        return this;
    }
}
