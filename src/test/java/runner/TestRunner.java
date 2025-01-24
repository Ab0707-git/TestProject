package runner;



import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/test/resources/features",  // Path to your feature files
    glue = "test",  // Package where your step definition files are located
    plugin = {"pretty", "html:target/cucumber-reports"},  // Generate pretty and HTML reports
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Test
    public void runCucumber() {
        // TestNG will run the Cucumber tests
    }
}
