package com.cydeo.avengers;

import com.cydeo.utility.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Homework2 extends HrTestBase {

    /**
     * TASK 2 :
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */
    @Test
    public void task2() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}").
                when().get("/employees").prettyPeek();


        //     * - Then status code is 200
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());

        //     * - And Content - Type is Json
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        //     * - And all job_ids start with 'SA'
        List<String> allJobIDs = response.path("items.job_id");
        System.out.println(allJobIDs);

        for (String eachJobID : allJobIDs) {
            System.out.println(eachJobID);
            Assertions.assertTrue(eachJobID.startsWith("SA"));
        }



        //     * - And all department_ids are 80
        //     * - Count is 25

    }
}
