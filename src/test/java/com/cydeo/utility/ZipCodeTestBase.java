package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class ZipCodeTestBase {

    @BeforeAll
    public static void init(){

        baseURI="https://api.zippopotam.us";

    }

    @AfterAll
    public static void destroy(){

        reset();

    }
}
