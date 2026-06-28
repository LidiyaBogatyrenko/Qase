package tests;

import models.CreateTestCase.CreateTestCaseRq;
import models.CreateTestCase.CreateTestCaseRs;
import org.testng.annotations.Test;

import static adapters.TestCaseAdapter.createTestCase;
import static org.testng.Assert.assertTrue;

public class TestCaseAPITest {

    CreateTestCaseRq rq = CreateTestCaseRq.builder()
            .stepsType("classic")
            .description("check create new test case")
            .preconditions("privet")
            .postconditions("poka")
            .title("possitive create test case")
            .severity(1)
            .priority(2)
            .behavior(3)
            .type(4)
            .layer(3)
            .isFlaky(1)
            .status(2)
            .createdAt("28-06-2026")
            .updatedAt("28-06-2026")
            .isManual(1)
            .automation(1)
            .isToBeAutomated(1)
            .build();

    @Test
    public void checkCreateTestCase() {
        CreateTestCaseRs rs = createTestCase(rq, "QATEST");
        assertTrue(rs.status);
    }
}