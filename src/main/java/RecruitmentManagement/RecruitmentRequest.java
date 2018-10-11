package RecruitmentManagement;

import EventManagement.EventPlan;
import UserManagement.LoginManager;
import UserManagement.User;
import Utils.Department;

public class RecruitmentRequest {
    private String title = "";
    private Department department = Department.Administration;
    private User user;
    private EventPlan eventPlan;
    private RecruitmentEmploymentForm recruitmentForm = RecruitmentEmploymentForm.Temporary;
    private int requiredExperienceYears;
    private String description = "";
    private RecruitmentStatus recruitmentStatus = RecruitmentStatus.RequestSent;
    private String comment = "";

    public RecruitmentRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public RecruitmentRequest setRequestingDeparment(Department department) {
        this.department = department;
        return this;
    }

    public RecruitmentRequest setUser(User user) {
        this.user = user;
        return this;
    }

    public RecruitmentRequest setEventPlan(EventPlan eventPlan) {
        this.eventPlan = eventPlan;
        return this;
    }

    public RecruitmentRequest setEmploymentForm(RecruitmentEmploymentForm recruitmentForm) {
        this.recruitmentForm = recruitmentForm;
        return this;
    }

    public RecruitmentRequest setRequiredExperienceYears(int numberOfYears) {
        this.requiredExperienceYears = numberOfYears;
        return this;
    }

    public RecruitmentRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return this.recruitmentStatus;
    }

    public String getTitle() {
        return this.title;
    }

    public RecruitmentRequest setStatus(RecruitmentStatus status) {
        this.recruitmentStatus = status;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RecruitmentRequest setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Recruitment request: ")
                .append(title).append("\n")
                .append("-------------------\n")
                .append("Status: ").append(recruitmentStatus).append("\n")
                .append("Department: ").append(department).append("\n")
                .append("User: ").append(user.getName()).append("\n")
                .append("Event: ").append(eventPlan.getName()).append("\n")
                .append("Employment form: ").append(recruitmentForm).append("\n")
                .append("Required experience: ").append(requiredExperienceYears).append("\n")
                .append("Description: ").append(description).append("\n")
                .append("Comment: ").append(comment).append("\n")
                .toString();
    }
}
