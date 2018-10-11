package RecruitmentManagement;

import UserManagement.User;

import java.util.ArrayList;

public class RecruitmentManager {
    private ArrayList<RecruitmentRequest> requests;

    public RecruitmentManager() {
        this.requests = new ArrayList<>();
    }

    public void addNewRecruitmentRequest(RecruitmentRequest r) {
        requests.add(r);
    }

    public ArrayList<RecruitmentRequest> getRecruits(RecruitmentStatus recruitmentStatus) {
        ArrayList<RecruitmentRequest> result = new ArrayList<>();

        for (RecruitmentRequest request : requests)
            if (request.getRecruitmentStatus().equals(recruitmentStatus))
                result.add(request);

        return result;
    }

    public ArrayList<RecruitmentRequest> getRecruits(User user) {
        ArrayList<RecruitmentRequest> result = new ArrayList<>();

        for (RecruitmentRequest request : requests)
            if (request.getUser().equals(user))
                result.add(request);

        return result;
    }

    public String createRecruitmentListing(ArrayList<RecruitmentRequest> recruits) {
        StringBuilder sb = new StringBuilder()
                .append("Recruitment Requests\n")
                .append("-------------------------\n");
        int index = 1;

        for (RecruitmentRequest request : recruits) {
            sb.append("(")
                    .append(index)
                    .append("): ")
                    .append(request.getTitle())
                    .append(" [")
                    .append(request.getRecruitmentStatus())
                    .append("]\n");
            index++;
        }
        return sb.toString();
    }

    public void deleteRequest(RecruitmentRequest request) {
        requests.remove(request);
    }
}
