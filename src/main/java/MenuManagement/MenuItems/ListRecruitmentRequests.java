package MenuManagement.MenuItems;

import RecruitmentManagement.RecruitmentManager;
import RecruitmentManagement.RecruitmentRequest;
import RecruitmentManagement.RecruitmentStatus;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.InputReader;

import java.util.ArrayList;

public class ListRecruitmentRequests extends MenuItem {

    private final LoginManager loginManager;
    private final RecruitmentManager recruitmentManager;

    public ListRecruitmentRequests(LoginManager loginManager, RecruitmentManager recruitmentManager) {
        this.loginManager = loginManager;
        this.recruitmentManager = recruitmentManager;

        addAuthorizedRole(UserRole.HRStaffMember);
        addAuthorizedRole(UserRole.ServiceDepartmentManager);
        addAuthorizedRole(UserRole.ProductionDepartmentManager);
    }

    @Override
    public String GetMenuItemName() {
        return "List Recruitment Requests";
    }

    @Override
    public void RunMenuItemFunction() {
        ArrayList<RecruitmentRequest> requests;
        RecruitmentRequest request;
        int choice;

        try {
            switch (loginManager.getUserRole()) {
                case HRStaffMember:
                    // Printing the requests
                    System.out.println("Select Request");
                    requests = recruitmentManager.getRecruits(RecruitmentStatus.RequestSent);

                    if (requests.size() < 1) {
                        System.out.println("No requests.");
                        return;
                    }

                    System.out.println(recruitmentManager.createRecruitmentListing(requests));

                    choice = Integer.parseInt(InputReader.readUserInput("Request Nr"));

                    if (choice < 1 || choice > requests.size())
                        throw new IllegalArgumentException("Not a valid number");

                    request = requests.get(choice - 1);

                    System.out.println(requests);
                    System.out.println("Select action:\n------------------");
                    System.out.println("(1): Approve with comment");
                    System.out.println("(2): Decline with comment");
                    System.out.println("(3): Decline with comment");
                    System.out.println("(4): Exit");

                    choice = Integer.parseInt(InputReader.readUserInput("Action"));

                    if (choice < 1 || choice > 4)
                        throw new IllegalArgumentException("Not a valid number");

                    switch (choice) {
                        case 1: // Approve
                            request.setStatus(RecruitmentStatus.ApprovedAndFound)
                                    .setComment(InputReader.readUserInput("Comment"));
                            break;
                        case 2: // Decline
                            request.setStatus(RecruitmentStatus.Declined)
                                    .setComment(InputReader.readUserInput("Comment"));
                            break;
                        case 3: // Delete
                            System.out.println("Deleting request");
                            recruitmentManager.deleteRequest(request);
                            break;
                    }

                    break;
                case ServiceDepartmentManager:
                case ProductionDepartmentManager:
                    requests = recruitmentManager.getRecruits(loginManager.getUser());

                    if (requests.size() < 1) {
                        System.out.println("No requests.");
                        return;
                    }

                    System.out.println(recruitmentManager.createRecruitmentListing(requests));

                    // Printing the requests
                    System.out.println("Select Request");
                    requests = recruitmentManager.getRecruits(loginManager.getUser());
                    System.out.println(recruitmentManager.createRecruitmentListing(requests));

                    choice = Integer.parseInt(InputReader.readUserInput("Request Nr"));

                    if (choice < 1 || choice > requests.size())
                        throw new IllegalArgumentException("Not a valid number");

                    request = requests.get(choice - 1);

                    System.out.println(requests);
                    System.out.println("Select action:\n------------------");
                    System.out.println("(1): Delete");
                    System.out.println("(2): Exit");

                    choice = Integer.parseInt(InputReader.readUserInput("Action"));

                    if (choice < 1 || choice > 3)
                        throw new IllegalArgumentException("Not a valid number");

                    switch (choice) {
                        case 1: // Delete
                            System.out.println("Deleting request");
                            recruitmentManager.deleteRequest(request);
                            break;
                    }

                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid choice. Try again.");
        }
    }
}
