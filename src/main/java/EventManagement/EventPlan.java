package EventManagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EventPlan {
    private String eventName = "";
    private String clientName = "";
    private EventPlanType eventPlanType = EventPlanType.Unknown;
    private LocalDateTime eventStart = LocalDateTime.now(), eventEnd = LocalDateTime.now();
    private int attendees = 0;
    private int budget = 0;
    private String comment = "";
    private EventStatus status = EventStatus.NewEventRequest;
    private String financialFeedback = "";

    @Override
    public String toString() {
        DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);

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

    public EventPlan setEventType(EventPlanType eventPlanType) {
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

    public void setStatus(EventStatus newStatus) {
        this.status = newStatus;
    }

    public EventStatus getStatus() {
        return this.status;
    }

    public void setFinancialFeedback(String financialFeedback) {
        this.financialFeedback = financialFeedback;
    }
}
