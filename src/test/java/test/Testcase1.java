package test;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Todo.TodoPage;

import static org.junit.Assert.*;


public class Testcase1 {

    WebDriver driver;
    TodoPage todoPage;

    @Before // 
    public void setUp() {
        String browser = System.getProperty("browser", "chrome"); // Default to chrome if not specified
        System.out.println("==> Setting up WebDriver before test execution...");
        
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get("https://todomvc.com/examples/react/dist/#/");
        driver.manage().window().maximize();
        todoPage = new TodoPage(driver); 
        System.out.println("==> WebDriver setup complete. Opened ToDoMVC app.");
    }

    @Given("User is ready to add Todo items")
    public void user_has_to_add_todo_items() {
        System.out.println("==> Step: User is ready to add ToDo items.");
    }

    // Updated to accept dynamic task input
    @When("User adds {string} in one")
    public void user_adds_task(String task) {
        System.out.println("==> Step: User is adding task: " + task);
        todoPage.addTodoItem(task); // 
        System.out.println("==> Task added successfully.");
    }

    @Then("The total number of tasks should be {int} in one")
    public void the_total_number_of_task_should_be(Integer expectedTotal) {
        System.out.println("==> Step: Verifying total number of tasks.");
        int actualTotal = todoPage.getTotalTaskCount();
        System.out.println("==> Expected Total: " + expectedTotal + ", Actual Total: " + actualTotal);
        assertEquals("The total number of tasks is incorrect!", expectedTotal.intValue(), actualTotal);
        System.out.println("==> Verification successful.");
    }

    @Then("close browser one")
    public void close_browser() {
        System.out.println("==> Step: Closing the browser.");
    }

    @After 
    public void tearDown() {
        System.out.println("==> Tearing down WebDriver and closing browser...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("==> Browser closed. Test execution complete.");
    }
}
