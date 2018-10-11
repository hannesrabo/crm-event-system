import FinancialRequestManagment.FinancialRequest;
import FinancialRequestManagment.FinancialRequestManager;
import FinancialRequestManagment.FinancialRequestStatus;
import Utils.Department;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class FinancialRequestTest {

    private FinancialRequest MockRequest() {
        FinancialRequest request = new FinancialRequest();

        request.setName("Balloon budget")
                .setDepartment(Department.Services)
                .setProjectReference("e12488")
                .setRequiredAmount(5000)
                .setReason("We need a lot of money for all of these balloons!");

        return request;
    }

    @Test
    public void CreateFinancialRequest() {
        FinancialRequest request = MockRequest();

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
                        "Reason: We need a lot of money for all of these balloons!\n" +
                        "Status: New\n",
                request.toString());

        FinancialRequestManager manager = new FinancialRequestManager();
        int i = manager.add(request);
        assertTrue(manager.getFinancialRequest(i).equals(request));
    }

    @Test
    public void FinancialRequestStatus() {
        FinancialRequest request = MockRequest();
        FinancialRequestManager manager = new FinancialRequestManager();
        manager.add(request);

        assertTrue(manager.getFinancialRequests(Department.Financial).iterator().hasNext());
        assertTrue(manager.getFinancialRequests(Department.Services).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Production).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Administration).iterator().hasNext());

        request.setStatus(FinancialRequestStatus.Approved);

        assertFalse(manager.getFinancialRequests(Department.Financial).iterator().hasNext());
        assertTrue(manager.getFinancialRequests(Department.Services).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Production).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Administration).iterator().hasNext());

        request.setStatus(FinancialRequestStatus.Rejected);

        assertFalse(manager.getFinancialRequests(Department.Financial).iterator().hasNext());
        assertTrue(manager.getFinancialRequests(Department.Services).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Production).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Administration).iterator().hasNext());

        request.setDepartment(Department.Production);

        assertFalse(manager.getFinancialRequests(Department.Financial).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Services).iterator().hasNext());
        assertTrue(manager.getFinancialRequests(Department.Production).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Administration).iterator().hasNext());

        request.setStatus(FinancialRequestStatus.New);

        assertTrue(manager.getFinancialRequests(Department.Financial).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Services).iterator().hasNext());
        assertTrue(manager.getFinancialRequests(Department.Production).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Administration).iterator().hasNext());

        request.setStatus(FinancialRequestStatus.Approved);

        assertFalse(manager.getFinancialRequests(Department.Financial).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Services).iterator().hasNext());
        assertTrue(manager.getFinancialRequests(Department.Production).iterator().hasNext());
        assertFalse(manager.getFinancialRequests(Department.Administration).iterator().hasNext());
    }
}
