package FinancialRequestManagment;

import Utils.Department;

import java.util.ArrayList;

public class FinancialRequestManager {
    private final ArrayList<FinancialRequest> financialRequests;

    public FinancialRequestManager() {
        financialRequests = new ArrayList<>();
    }

    public int add(FinancialRequest request) {
        financialRequests.add(request);
        return financialRequests.size() - 1;
    }

    public FinancialRequest getFinancialRequest(int i) {
        return financialRequests.get(i);
    }

    private ArrayList<FinancialRequest> filterForDepartment(Department department, ArrayList<FinancialRequest> list) {
        ArrayList<FinancialRequest> result = new ArrayList<>();
        for (FinancialRequest f : list) {
            if (f.getDepartment() == department) {
                result.add(f);
            }
        }

        return result;
    }

    public ArrayList<FinancialRequest> getFinancialRequests(FinancialRequestStatus status) {
        ArrayList<FinancialRequest> result = new ArrayList<>();
        for (FinancialRequest f : financialRequests) {
            if (f.getStatus() == status) {
                result.add(f);
            }
        }

        return result;
    }

    public ArrayList<FinancialRequest> getFinancialRequests(Department department) {
        switch (department) {
            case Financial:
                return getFinancialRequests(FinancialRequestStatus.New);
            case Production:
            case Services:
                ArrayList<FinancialRequest> result = getFinancialRequests(FinancialRequestStatus.New);
                result.addAll(getFinancialRequests(FinancialRequestStatus.Approved));
                result.addAll(getFinancialRequests(FinancialRequestStatus.Rejected));
                return filterForDepartment(department, result);
        }

        return new ArrayList<>();
    }

}
