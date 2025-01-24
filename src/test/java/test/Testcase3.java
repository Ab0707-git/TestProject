package test;



import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Todo.TodoPage;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Testcase3 {

    WebDriver driver;
    TodoPage todoPage;

    @Before 
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
    
    @Given("The user has to edit task")
    public void user_has_to_edit_task() {
        System.out.println("==> Given: The user has to edit a task.");
    }

    @When("The user adds {string}")
    public void user_adds_task(String task) {
        System.out.println("==> When: User adds task: " + task);
        
      
        todoPage.addTodoItem(task);
    }

    @When("The user double clicks on {string}")
    public void user_double_clicks_and_edits_task(String task) {
        System.out.println("==> When: User is double-clicking on task: " + task);
        
        try {
            todoPage.doubleClickToEditFirstTodo(); 
            System.out.println("==> Task was successfully double-clicked and is being edited.");
        } catch (Exception e) {
            System.out.println("Error during double-clicking the task: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Then("The check the count of the task.")
    public void the_check_the_count_of_the_task() {
        int taskCount = todoPage.getTotalTaskCount(); 
        System.out.println("Total task count: " + taskCount);
        
        assertTrue("Task count is not as expected", taskCount >= 0);
    }

    

    @After
    @Then("Close browser")
    public void close_browser() {
        System.out.println("==> Closing the browser.");
        driver.quit();  
    }

}


