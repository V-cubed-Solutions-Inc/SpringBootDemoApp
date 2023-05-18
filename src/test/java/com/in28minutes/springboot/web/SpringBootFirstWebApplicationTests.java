package com.in28minutes.springboot.web;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootFirstWebApplication.class)
@SpringBootTest
public class SpringBootFirstWebApplicationTests {
    private final String overtimeWarning = "You already have 10 hours of work for the specified date and your manager doesn't allow you overtime.";
    private final String protocol = "http";
    private final String serverHost = "127.0.0.1";
    private final String serverPort = "8082";
    private WebDriver driver;
    private ConfigurableApplicationContext appContext;

    @Before
    public void setUp() {
        appContext = SpringApplication.run(SpringBootFirstWebApplication.class);
        System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);  
        driver.manage().window().maximize();  
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
        appContext.stop();
        appContext.close();
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


