import FinancialRequestManagment.FinancialRequest;
import FinancialRequestManagment.FinancialRequestManager;
import Utils.Department;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FinancialRequestTest {

    @Test
    public void CreateFinancialRequest() {
        FinancialRequest request = new FinancialRequest();

        request.setName("Balloon budget")
                .setDepartment(Department.Services)
                .setProjectReference("e12488")
                .setRequiredAmount(5000)
                .setReason("We need a lot of money for all of these balloons!");

        assertEquals(
                "Financial request: Balloon budget\n" +
                        "--------------------\n" +
                        "Department: Services\n" +
                        "Project Reference: e12488\n" +
                        "Required Amount (SEK): 5000\n" +
                        "Reason: We need a lot of money for all of these balloons!\n",
                request.toString());

        FinancialRequestManager manager = new FinancialRequestManager();
        int i = manager.add(request);
        assertTrue(manager.getFinancialRequest(i).equals(request));
    }
}
