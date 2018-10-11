package MenuManagement.MenuItems;

import FinancialRequestManagment.FinancialRequest;
import FinancialRequestManagment.FinancialRequestManager;
import UserManagement.LoginManager;
import UserManagement.UserRole;
import Utils.InputReader;

import static Utils.DepartmentUtils.roleToDepartment;

public class CreateFinancialRequestMenuItem extends MenuItem {

    private final LoginManager loginManager;
    private final FinancialRequestManager financialRequestManager;

    public CreateFinancialRequestMenuItem(LoginManager loginManager, FinancialRequestManager financialRequestManager) {
        this.loginManager = loginManager;
        this.financialRequestManager = financialRequestManager;

        addAuthorizedRole(UserRole.ProductionDepartmentManager);
        addAuthorizedRole(UserRole.ServiceDepartmentManager);
    }

    @Override
    public String GetMenuItemName() {
        return "Create financial request";
    }

    @Override
    public void RunMenuItemFunction() {
        System.out.println("Create financial request: \n" +
                "------------------------");
        try {
            FinancialRequest request = new FinancialRequest()
                    .setDepartment(roleToDepartment(loginManager.getUserRole()))
                    .setName(InputReader.readUserInput("Financial Request Name"))
                    .setProjectReference(InputReader.readUserInput("Project Reference"))
                    .setRequiredAmount(Integer.parseInt(InputReader.readUserInput("Required amount (SEK)")))
                    .setReason(InputReader.readUserInput("Reason"));

            financialRequestManager.add(request);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Number.");
        }
    }
}
