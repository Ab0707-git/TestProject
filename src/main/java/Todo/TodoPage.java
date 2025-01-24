package Todo;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TodoPage {
    private WebDriver driver;
    private WebDriverWait wait; 

    // Locators
    private By todoInput = By.id("todo-input");
    private By firstTodoLabel = By.xpath("//*[@id=\"root\"]/main/ul/li[1]/div/label");
    private By secondTodoCheckbox = By.xpath("//*[@id=\"root\"]/main/ul/li[2]/div/input");
    private By clearCompletedLink = By.xpath("//*[@id=\"root\"]/footer/ul/li[3]/a");
    private By activeTodosLink = By.xpath("//*[@id=\"root\"]/footer/ul/li[1]/a");
    private By footerButton = By.xpath("//*[@id=\"root\"]/footer/button");
    private By totalTaskCount = By.xpath("//span[@class='todo-count']");

    // Constructor
    public TodoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize Explicit Wait
    }

    // Method to get the input field
    public WebElement getInputField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(todoInput));
    }

    // Method to add a ToDo item
    public void addTodoItem(String todo) {
        WebElement inputField = getInputField();
        inputField.sendKeys(todo);
        inputField.sendKeys(Keys.ENTER);
    }

    public void markSecondTodoAsComplete() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(secondTodoCheckbox));
        checkbox.click();
    }

    public void clearCompletedTodos() {
        WebElement clearLink = wait.until(ExpectedConditions.elementToBeClickable(clearCompletedLink));
        clearLink.click();
    }
       // Edit Task
    public void doubleClickToEditFirstTodo() {
        WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(firstTodoLabel));
        Actions actions = new Actions(driver);
        actions.doubleClick(label)
               .sendKeys(" at 12 pm")
               .sendKeys(Keys.ENTER)
               .perform();
    }

    public void navigateToActiveTodos() {
        WebElement activeTodos = wait.until(ExpectedConditions.elementToBeClickable(activeTodosLink));
        activeTodos.click();
    }

    public void clickFooterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(footerButton));
        button.click();
    }

    // Method to get total task count
    public int getTotalTaskCount() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement taskCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalTaskCount));
        String taskCountText = taskCountElement.getText();
        return Integer.parseInt(taskCountText.split(" ")[0]);
    }

    public String getFirstTodoTask() {
        // Wait until the first todo label is visible
        WebElement firstTodoLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(this.firstTodoLabel));
        return firstTodoLabel.getText(); 
    }

}
