package EventManagement;

public enum TaskStatus {
    Active, Done;

    public static String valueListing() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (TaskStatus status : values()) {
            sb.append(status.toString()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
