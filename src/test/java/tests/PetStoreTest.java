package tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreTest {

    @Test
    public void checkAddWithEmptyBody() {
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .body("")
                .log().all()
        .when()
                .post("/v2/pet")
        .then()
                .log().all()
                .statusCode(405)
                .body("code", equalTo(405))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("no data"));
    }

    @Test
    public void checkCRUD() {
        //CREATE
    long id = given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .body("{\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"Tomas\"\n" +
                        "  },\n" +
                        "  \"name\": \"cat\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"wwww.avito.ru\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"street\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .log().all()
        .when()
                .post("/v2/pet")
        .then()
                .log().all()
                .statusCode(200)
                        .extract()
                                .path("id");
    //READ
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .pathParam("id", id)
                .log().all()
                .when()
                .get("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200);
        //DELETE
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .pathParam("id", id)
                .log().all()
                .when()
                .delete("/v2/pet/{id}")
                .then()
                .log().all()
                .statusCode(200);
    }
}