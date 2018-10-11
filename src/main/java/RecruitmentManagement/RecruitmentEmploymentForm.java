package RecruitmentManagement;

import EventManagement.TaskStatus;

public enum RecruitmentEmploymentForm {
    Temporary, Permanent;

    public static String createListing() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (RecruitmentEmploymentForm status : values()) {
            sb.append(status.toString()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
