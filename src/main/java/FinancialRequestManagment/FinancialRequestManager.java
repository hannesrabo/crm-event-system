package FinancialRequestManagment;

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

}
