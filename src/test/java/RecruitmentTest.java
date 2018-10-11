import EventManagement.EventPlan;
import RecruitmentManagement.RecruitmentEmploymentForm;
import RecruitmentManagement.RecruitmentManager;
import RecruitmentManagement.RecruitmentRequest;
import RecruitmentManagement.RecruitmentStatus;
import UserManagement.LoginManager;
import Utils.DepartmentUtils;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecruitmentTest {

    @Test
    public void createRecruitmentRequest() {
        EventPlan ep = EventPlanTest.getNewEventPlan();
        LoginManager lm = LoginTest.createLoginManager();
        RecruitmentManager recruitmentManager = new RecruitmentManager();

        RecruitmentRequest r = new RecruitmentRequest()
                .setTitle("New employee needed now!")
                .setRequestingDeparment(DepartmentUtils.roleToDepartment(lm.getUserRole()))
                .setUser(lm.getUser())
                .setEventPlan(ep)
                .setEmploymentForm(RecruitmentEmploymentForm.Temporary)
                .setRequiredExperienceYears(5)
                .setDescription("Short description");

        assertEquals("Recruitment request: New employee needed now!\n" +
                        "-------------------\n" +
                        "Status: RequestSent\n" +
                        "Department: Production\n" +
                        "User: hrabo\n" +
                        "Event: testEvent\n" +
                        "Employment form: Temporary\n" +
                        "Required experience: 5\n" +
                        "Description: Short description\n" +
                        "Comment: \n",
                r.toString());

        recruitmentManager.addNewRecruitmentRequest(r);

        ArrayList<RecruitmentRequest> recruits = recruitmentManager.getRecruits(RecruitmentStatus.RequestSent);

        assertTrue(recruits.size() == 1);
        assertTrue(recruitmentManager.getRecruits(RecruitmentStatus.ApprovedAndFound).size() == 0);

        String listing = recruitmentManager.createRecruitmentListing(recruits);

        assertEquals("Recruitment Requests\n" +
                        "-------------------------\n" +
                        "(1): New employee needed now! [RequestSent]\n",
                        listing);

        r.setStatus(RecruitmentStatus.ApprovedAndFound);

        assertTrue(recruitmentManager.getRecruits(RecruitmentStatus.RequestSent).size() == 0);
        assertTrue(recruitmentManager.getRecruits(RecruitmentStatus.ApprovedAndFound).size() == 1);


        listing = recruitmentManager.createRecruitmentListing(recruitmentManager.getRecruits(RecruitmentStatus.ApprovedAndFound));

        assertEquals("Recruitment Requests\n" +
                        "-------------------------\n" +
                        "(1): New employee needed now! [ApprovedAndFound]\n",
                        listing);

    }
}
