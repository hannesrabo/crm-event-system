package EventManagement;

public enum EventPlanType {
    WorkShop,
    Unknown;

    public static EventPlanType getEnumFromString(String str) {
        try {
            return EventPlanType.valueOf(str);
        } catch (IllegalArgumentException e) {
            return EventPlanType.Unknown;
        }
    }
}
