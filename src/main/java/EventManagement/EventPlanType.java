package EventManagement;

public enum EventPlanType {
    WorkShop,
    Unknown;

    public static EventPlanType getEnumFromString(String str) {
        try {
            EventPlanType eventPlanType = EventPlanType.valueOf(str);
            return eventPlanType;
        } catch (IllegalArgumentException e) {
            return EventPlanType.Unknown;
        }
    }
}
