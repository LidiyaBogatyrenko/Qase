package adapters;

import io.restassured.module.jsv.JsonSchemaValidator;
import models.CreateProject.CreateProjectRq;
import models.CreateProject.CreateProjectRs;
import models.DeleteProject.DeleteProjectRs;
import models.GetProject.GetProjectRs;

import static io.restassured.RestAssured.given;

public class ProjectAdapter extends BaseAdapter {


    public static CreateProjectRs createProject(CreateProjectRq projectRq) {
        return given()
                .spec(spec)
                .body(gson.toJson(projectRq))
                .log().all()
        .when()
                .post("/v1/project")
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/createProjectSchemaRs.json"))
                .log().all()
                .spec(ok200)
                .extract()
                .as(CreateProjectRs.class);
    }

    public static DeleteProjectRs deleteProject(String code) {
       return given()
                .spec(spec)
                .pathParam("code", code)
                .log().all()
        .when()
                .delete("/v1/project/{code}")
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/deleteProjectSchemaRs.json"))
                .log().all()
                .spec(ok200)
                .extract()
                .as(DeleteProjectRs.class);
    }

    public static GetProjectRs getProject(String code) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .log().all()
        .when()
                .get("/v1/project/{code}")
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/getProjectSchemaRs.json"))
                .log().all()
                .spec(ok200)
                .extract()
                .as(GetProjectRs.class);
    }
}