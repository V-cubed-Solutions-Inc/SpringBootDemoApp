package com.in28minutes.springboot.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBootFirstWebApplication.class)
@SpringBootTest
public class SpringBootFirstWebApplicationTests {
    private final String overtimeWarning = "You already have 10 hours of work for the specified date and your manager doesn't allow you overtime.";
    private final String protocol = "https";
    private final String serverHost = "www.blazedemo.com";
    private final String serverPort = "8082";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginSuccess() {
        login(false, true);
        assert driver.findElement(By.id("logout")).isDisplayed();
    }

    @Test
    public void testLoginFailure() {
        login(false, false);
        assert driver.getPageSource().contains("Your login attempt was not successful, try again.");
    }

    @Test
    public void testEnableOvertime() {
        overtime(true);
        addTodo();
        assert !driver.getPageSource().contains(overtimeWarning);
    }

    @Test
    public void testDisableOvertime() {
        overtime(false);
        addTodo();
        assert driver.getPageSource().contains(overtimeWarning);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // helper functions

    private void login(Boolean isAdmin, Boolean success) {
        String root;
        if (this.protocol.equals("http")) {
            root = String.format("%s://%s:%s", this.protocol, this.serverHost, this.serverPort);
        } else {
            root = String.format("%s://%s", this.protocol, this.serverHost);
        }

        driver.get(String.format("%s/login", root));
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        if (isAdmin) usernameField.sendKeys("DemoAdmin");
        else usernameField.sendKeys("DemoUser1");
        if (success) passwordField.sendKeys("password");
        else passwordField.sendKeys("wrongpassword");
        driver.findElement(By.name("submit")).click();
    }

    private void overtime(Boolean enable) {
        login(true, true);
        if (enable) driver.findElement(By.id("overtime-on")).click();
        else driver.findElement(By.id("overtime-off")).click();
        driver.findElement(By.id("logout")).click();
    }

    private void addTodo() {
        login(false, true);
        driver.findElement(By.id("todos")).click();
        driver.findElement(By.id("add-todo-btn")).click();
        WebElement hours = driver.findElement(By.id("hoursRequired"));
        hours.clear();
        hours.sendKeys("50");
        driver.findElement(By.id("add-todo-btn-submit")).click();
    }
}


