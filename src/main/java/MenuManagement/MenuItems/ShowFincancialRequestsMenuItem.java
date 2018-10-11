package MenuManagement.MenuItems;

import FinancialRequestManagment.FinancialRequest;
import FinancialRequestManagment.FinancialRequestManager;
import UserManagement.UserRole;
import Utils.InputReader;

import java.util.ArrayList;

public class ShowFincancialRequestsMenuItem extends MenuItem {

    private final FinancialRequestManager financialRequestManager;

    public ShowFincancialRequestsMenuItem(FinancialRequestManager financialRequestManager) {
        this.financialRequestManager = financialRequestManager;

        addAuthorizedRole(UserRole.FinancialManager);
    }

    @Override
    public String GetMenuItemName() {
        return "List financial requests";
    }

    @Override
    public void RunMenuItemFunction() {
        System.out.println("Financial requests: \n--------------------");
        int index = 1;
        ArrayList<FinancialRequest> financialRequests = financialRequestManager.getFinancialRequests();

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
            }

            // We use human indexing starting at 1 which means we have to subtract one here.
            System.out.println("\n" + financialRequests.get(index - 1));

            editFinancialRequest(financialRequests.get(index - 1));

        } catch (IllegalArgumentException e) {
            System.out.println("Not a valid index.");
            return;
        }
    }

    private void editFinancialRequest(FinancialRequest financialRequest) {

    }
}
