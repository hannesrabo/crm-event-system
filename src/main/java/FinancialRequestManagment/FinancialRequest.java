package FinancialRequestManagment;

import Utils.Department;

public class FinancialRequest {
    private String name;
    private Department department;
    private String projectReference;
    private int requiredAmount;
    private String reason;

    private FinancialRequestStatus status = FinancialRequestStatus.New;

    @Override
    public String toString() {
        return String.join("",
                "Financial request: ",  name,
                "\n--------------------\n",
                "Department: ", department.toString(),
                "\nProject Reference: ", projectReference,
                "\nRequired Amount (SEK): ", Integer.toString(requiredAmount),
                "\nReason: ", reason,
                "\nStatus: ", status.toString(),
                "\n");
    }

    public FinancialRequest setName(String name) {
        this.name = name;
        return this;
    }

    public FinancialRequest setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public FinancialRequest setProjectReference(String projectReference) {
        this.projectReference = projectReference;
        return this;
    }

    public FinancialRequest setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
        return this;
    }

    public FinancialRequest setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public FinancialRequest setStatus(FinancialRequestStatus status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public FinancialRequestStatus getStatus() { return status; }
}
