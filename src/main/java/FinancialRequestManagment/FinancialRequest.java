package FinancialRequestManagment;

import Utils.Department;

public class FinancialRequest {
    String name;
    Department department;
    String projectReference;
    int requiredAmount;
    String reason;

    @Override
    public String toString() {
        return String.join("",
                "Financial request: ",  name,
                "\n--------------------\n",
                "Department: ", department.toString(),
                "\nProject Reference: ", projectReference,
                "\nRequired Amount (SEK): ", Integer.toString(requiredAmount),
                "\nReason: ", reason,
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
}
