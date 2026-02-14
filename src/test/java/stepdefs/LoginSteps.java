package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("user is on login page")
    public void user_is_on_login_page() {
        loginPage.open();
    }

    @When("user logs in with valid credentials")
    public void user_logs_in() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        Thread.sleep(10000);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
