package tests;

import adapters.ProjectAdapter;
import models.CreateProject.CreateProjectRq;
import models.CreateProject.CreateProjectRs;
import models.DeleteProject.DeleteProjectRs;
import models.GetProject.GetProjectRs;
import org.testng.annotations.Test;

import static adapters.ProjectAdapter.createProject;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProjectAPITest {

    CreateProjectRq rq = CreateProjectRq.builder()
            .title("ITM5")
            .code("ITM")
            .description("testITM")
            .group("tests")
            .access("all")
            .build();


    @Test
    public void checkCreateGetDeleteProject() {
        //создаём проект
        CreateProjectRs rs = createProject(rq);
        assertTrue(rs.status);
        assertEquals(rs.result.code, "ITM");
        //получаем проект и проверяем значения в ответе
        GetProjectRs getProjectRs = ProjectAdapter.getProject("ITM");
        assertEquals(getProjectRs.status, true);
        assertEquals(getProjectRs.result.title, "ITM5");
        assertEquals(getProjectRs.result.code, "ITM");
        assertEquals(getProjectRs.result.counts.cases, 0);
        assertEquals(getProjectRs.result.counts.suites, 0);
        assertEquals(getProjectRs.result.counts.milestones, 0);
        assertEquals(getProjectRs.result.counts.runs.total, 0);
        assertEquals(getProjectRs.result.counts.runs.active, 0);
        assertEquals(getProjectRs.result.counts.defects.total, 0);
        assertEquals(getProjectRs.result.counts.defects.open, 0);
        //удаляем проект
        DeleteProjectRs deleteProjectRs = ProjectAdapter.deleteProject("ITM");
        assertTrue(deleteProjectRs.status);
    }
}