package com.in28minutes.springboot.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootFirstWebApplicationTests {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginSuccess() {
        // retrieve login page
        driver.get("http://localhost:8082/login");

        // get login form elements
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoAdmin");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // check for logout button in page source
        WebElement logoutButton = driver.findElement(By.id("logout"));
        assert logoutButton.isDisplayed();
    }

    @Test
    public void testLoginFailure() {
        // retrieve login page
        driver.get("http://localhost:8082/login");

        // get login form elements
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoAdmin");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("wrong_password");

        // submit login form
        submitButton.click();

        // check for logout button in page source (should still be on login page)
        assert driver.getPageSource().contains("Your login attempt was not successful, try again.");
    }

    @Test
    public void testAllowOvertime() {
        // retrieve login page
        driver.get("http://localhost:8082/login");

        // login as admin (check element names)
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoAdmin");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // allow overtime
        WebElement allowOvertimeButton = driver.findElement(By.id("overtime-on"));
        allowOvertimeButton.click();

        // logout
        WebElement logoutButton = driver.findElement(By.id("logout"));
        logoutButton.click();

        // login as user
        usernameField = driver.findElement(By.name("username"));
        passwordField = driver.findElement(By.name("password"));
        submitButton = driver.findElement(By.name("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoUser1");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // go to todos page
        WebElement todosButton = driver.findElement(By.id("todos"));
        todosButton.click();

        // add a new todo
        WebElement addTodoButton = driver.findElement(By.id("add-todo-btn"));
        addTodoButton.click();

        // Enter more than 8 hour limit before overtime
        WebElement hoursField = driver.findElement(By.id("hoursRequired"));

        // enter hours
        hoursField.clear();
        hoursField.sendKeys("9");

        // submit new todo 
        WebElement submitTodoButton = driver.findElement(By.id("add-todo-btn-submit"));
        submitTodoButton.click();

        WebElement todoTable = driver.findElement(By.id("todo-table"));
        assert todoTable.isDisplayed();
    }

    @Test
    public void testDisableOvertime() {
        // retrieve login page
        driver.get("http://localhost:8082/login");

        // login as admin (check element names)
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoAdmin");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // allow overtime
        WebElement allowOvertimeButton = driver.findElement(By.id("overtime-off"));
        allowOvertimeButton.click();

        // logout
        WebElement logoutButton = driver.findElement(By.id("logout"));
        logoutButton.click();

        // login as user
        usernameField = driver.findElement(By.name("username"));
        passwordField = driver.findElement(By.name("password"));
        submitButton = driver.findElement(By.name("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoUser1");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // go to todos page
        WebElement todosButton = driver.findElement(By.id("todos"));
        todosButton.click();

        // add a new todo
        WebElement addTodoButton = driver.findElement(By.id("add-todo-btn"));
        addTodoButton.click();

        // Enter more than 8 hour limit before overtime
        WebElement hoursField = driver.findElement(By.id("hoursRequired"));

        // enter hours
        hoursField.clear();
        hoursField.sendKeys("9");

        // submit new todo
        WebElement submitTodoButton = driver.findElement(By.id("add-todo-btn-submit"));
        submitTodoButton.click();

        // check for overtime warning
        assert driver.getPageSource().contains("You already have 10 hours of work for the specified date and your manager doesn't allow you overtime.");
//        assert driver.getPageSource().contains("You already have 40 hours of work for the specified week and your manager doesn't allow you overtime.");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
