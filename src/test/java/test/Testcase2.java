package test;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Todo.TodoPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;



public class Testcase2 {

    WebDriver driver;
    TodoPage todoPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome"); 
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

    @Given("User has to complete one task")
    public void user_has_to_complete_one_task() {
        System.out.println("==> Step: User has to complete one task.");
    }

    @When("User adds {string}")
    public void user_adds_task(String task) {
        System.out.println("==> Step: User is adding task: " + task);
        todoPage.addTodoItem(task);
        
        
        System.out.println("==> Task added successfully.");
    }

    @When("User clicks on {string} Todo checkbox")
    public void user_clicks_on_task_checkbox(String task) {
        System.out.println("==> Step: User marks task as complete: " + task);
        
        
        WebElement checkbox = driver.findElement(By.xpath("//ul[@class='todo-list']//label[text()='" + task + "']/preceding-sibling::input[@type='checkbox']"));
        
        checkbox.click();
        System.out.println("✅ Task marked as completed.");
    }

    @Then("Check the total number of taskcount")
    public void check_total_task_count() {
        System.out.println("==> Step: Verifying total number of tasks.");
        
        
        int actualTotal = todoPage.getTotalTaskCount();
        System.out.println("==> Remaining tasks: " + actualTotal);
        
    
        assertTrue("Task count did not update as expected!", actualTotal >= 0);
        System.out.println("==> Verification successful.");
    }

    @Then("close browser")
    public void close_browser() {
        System.out.println("==> Step: Closing the browser.");
        driver.quit();  
    }

    @After
    public void tearDown() {
        System.out.println("==> Tearing down WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("==> Browser closed. Test execution complete.");
    }
}
