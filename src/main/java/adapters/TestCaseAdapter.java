package adapters;

import io.restassured.module.jsv.JsonSchemaValidator;
import models.CreateTestCase.CreateTestCaseRq;
import models.CreateTestCase.CreateTestCaseRs;

import static io.restassured.RestAssured.given;

public class TestCaseAdapter extends BaseAdapter {
    public static CreateTestCaseRs createTestCase (CreateTestCaseRq createTestCaseRq, String code) {
        return given()
                .spec(spec)
                .pathParam("code", code)
                .body(gson.toJson(createTestCaseRq))
                .log().all()
        .when()
                .post("v1/case/{code}")
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/createTestCaseSchemaRs.json"))
                .log().all()
                .spec(ok200)
                .extract()
                .as(CreateTestCaseRs.class);
    }
}