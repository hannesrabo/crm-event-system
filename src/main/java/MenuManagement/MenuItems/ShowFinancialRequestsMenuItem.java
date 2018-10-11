package MenuManagement.MenuItems;

import FinancialRequestManagment.FinancialRequest;
import FinancialRequestManagment.FinancialRequestManager;
import FinancialRequestManagment.FinancialRequestStatus;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.InputReader;

import java.util.ArrayList;

import static Utils.DepartmentUtils.roleToDepartment;

public class ShowFinancialRequestsMenuItem extends MenuItem {

    private final FinancialRequestManager financialRequestManager;
    private final LoginManager loginManager;

    public ShowFinancialRequestsMenuItem(FinancialRequestManager financialRequestManager, LoginManager loginManager) {
        this.financialRequestManager = financialRequestManager;
        this.loginManager = loginManager;

        addAuthorizedRole(UserRole.FinancialManager);
        addAuthorizedRole(UserRole.ProductionDepartmentManager);
        addAuthorizedRole(UserRole.ServiceDepartmentManager);
    }

    @Override
    public String GetMenuItemName() {
        return "List financial requests";
    }

    @Override
    public void RunMenuItemFunction() {
        System.out.println("Financial requests: \n--------------------");
        int index = 1;
        ArrayList<FinancialRequest> financialRequests = financialRequestManager.getFinancialRequests(
                roleToDepartment(loginManager.getUserRole()));

        if (financialRequests.size() < 1) {
            System.out.println("No financial requests to show.\n");
            return;
        }

        for (FinancialRequest f : financialRequests) {
            System.out.println("(" + index + "): " + f.getName());
            index++;
        }

        System.out.println();

        try {
            index = Integer.parseInt(InputReader.readUserInput("Select index"));

            if (index < 0 || index > financialRequests.size()) {
                System.out.println("Index does not exist");
                return;
            }

            // We use human indexing starting at 1 which means we have to subtract one here.
            System.out.println("\n" + financialRequests.get(index - 1));

            if (loginManager.getUserRole() == UserRole.FinancialManager) {
                editFinancialRequest(financialRequests.get(index - 1));
            }

        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("Not a valid index.");
            return;
        }
    }

    private void editFinancialRequest(FinancialRequest financialRequest) {
        System.out.println("(1) Approve");
        System.out.println("(2) Reject");
        System.out.println("(3) Exit");

        try {
            int choice = Integer.parseInt(InputReader.readUserInput(""));
            if (choice != 1 && choice != 2 && choice != 3)
                throw new IllegalArgumentException();

            if (choice == 1) {
                financialRequest.setStatus(FinancialRequestStatus.Approved);
            } else if (choice == 2) {
                financialRequest.setStatus(FinancialRequestStatus.Rejected);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid number.");
        }
    }
}
