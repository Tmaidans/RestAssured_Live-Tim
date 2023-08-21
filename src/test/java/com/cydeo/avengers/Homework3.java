package com.cydeo.avengers;

import com.cydeo.utility.ZipCodeTestBase;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Homework3 extends ZipCodeTestBase {

    /**
     * TASK 1
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     * post code is 22031
     * country is United States
     * country abbreviation is US
     * place name is Fairfax
     * state is Virginia
     */

    @Test
    public void task1() {

        given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("zipCode",22031).
        when().get("/us/{zipCode}").prettyPeek().
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server","cloudflare")
                .header("Report-To",notNullValue())
                .body("'post code'",is("22031"))
                .body("country",is("United States"))
                .body("'country abbreviation'",is("US"))
                .body("places[0].'place name'",is("Fairfax"))
                .body("places[0].state",is("Virginia"));


    }

    @Test
    public void task1JsonPath() {

        JsonPath jp = given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("zipCode", 22031).
                when().get("/us/{zipCode}").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server", "cloudflare")
                .header("Report-To", notNullValue())
                .extract().jsonPath();

        // GET JSON PATH TO START ASSERTIONS


        //     * post code is 22031
        Assertions.assertEquals("22031",jp.getString("'post code'"));

        //     * country is United States
        Assertions.assertEquals("United States",jp.getString("country"));

        //     * country abbreviation is US
        Assertions.assertEquals("US",jp.getString("'country abbreviation'"));

        //     * place name is Fairfax
        Assertions.assertEquals("Fairfax",jp.getString("places[0].'place name'"));

        //     * state is Virginia
        Assertions.assertEquals("Virginia",jp.getString("places[0].state"));


    }
}
