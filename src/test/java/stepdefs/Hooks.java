package stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.restassured.RestAssured;

public class Hooks {

    @Before("@api")
    public void setupAPI() {
        RestAssured.useRelaxedHTTPSValidation();
        System.out.println("✅ API Test Setup Complete");
    }

    @After("@api")
    public void teardownAPI() {
        System.out.println("✅ API Test Completed");
    }
}
