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
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

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
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoAdmin");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("wrong_password");

        // submit login form
        submitButton.click();

        // check for logout button in page source (should still be on login page)
        WebElement logoutButton = driver.findElement(By.id("logout"));
        assert !logoutButton.isDisplayed();
    }

    @Test
    public void testAllowOvertime() {
        // retrieve login page
        driver.get("http://localhost:8082/login");

        // login as admin (check element names)
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoAdmin");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // allow overtime
        WebElement allowOvertimeButton = driver.findElement(By.id("allow-overtime"));
        allowOvertimeButton.click();

        // logout
        WebElement logoutButton = driver.findElement(By.id("logout"));
        logoutButton.click();

        // login as user
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoUser");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // go to todos page
        WebElement todosButton = driver.findElement(By.id("todos"));
        todosButton.click();

        // add a new todo
        WebElement addTodoButton = driver.findElement(By.id("new-todo"));
        addTodoButton.click();

        // Enter more than 8 hour limit before overtime
        WebElement hoursField = driver.findElement(By.id("hours"));

        // enter hours
        hoursField.clear();
        hoursField.sendKeys("9");

        // submit new todo 
        WebElement submitTodoButton = driver.findElement(By.id("add-todo"));
        submitTodoButton.click();

        // check for overtime warning
        assert driver.getPageSource().contains("You already have 8 hours of work for the specified date and your manager doesn't allow you overtime.");
    }

    @Test
    public void testDisableOvertime() {
        // retrieve login page
        driver.get("http://localhost:8082/login");

        // login as admin (check element names)
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoAdmin");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // allow overtime
        WebElement disableOvertimeButton = driver.findElement(By.id("disable-overtime"));
        disableOvertimeButton.click();

        // logout
        WebElement logoutButton = driver.findElement(By.id("logout"));
        logoutButton.click();

        // login as user
        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.id("submit"));

        // enter username
        usernameField.clear();
        usernameField.sendKeys("DemoUser");

        // enter password
        passwordField.clear();
        passwordField.sendKeys("password");

        // submit login form
        submitButton.click();

        // go to todos page
        WebElement todosButton = driver.findElement(By.id("todos"));
        todosButton.click();

        // add a new todo
        WebElement addTodoButton = driver.findElement(By.id("new-todo"));
        addTodoButton.click();

        // Enter more than 8 hour limit before overtime
        WebElement hoursField = driver.findElement(By.id("hours"));

        // enter hours
        hoursField.clear();
        hoursField.sendKeys("9");

        // submit new todo 
        WebElement submitTodoButton = driver.findElement(By.id("add-todo"));
        submitTodoButton.click();

        // check for overtime warning
        assert !driver.getPageSource().contains("You already have 8 hours of work for the specified date and your manager doesn't allow you overtime.");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
