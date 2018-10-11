package Utils;

import UserManagement.UserRole;

public class DepartmentUtils {

    public static Department roleToDepartment(UserRole role) {
        switch (role) {
            case FinancialManager:
                return Department.Financial;
            case ProductionDepartmentManager:
            case ProductionDepartmentMember:
                return Department.Production;
            case AdministrationDepartmentManager:
            case SeniorCustomerOfficer:
            case CustomerServiceOfficer:
                return Department.Administration;
            case ServiceDepartmentManager:
                return Department.Services;
        }
        return Department.Administration;
    }

}
