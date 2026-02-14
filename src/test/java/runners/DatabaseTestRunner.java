package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/database.feature",
        glue = {"stepdefs"},
        tags = "@database",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/database-report.html",
                "json:target/cucumber-reports/database-report.json",
                "junit:target/cucumber-reports/database-report.xml"
        },
        monochrome = true
)
public class DatabaseTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
