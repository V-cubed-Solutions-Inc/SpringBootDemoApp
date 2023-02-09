from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By


class SpringBootFirstWebApplicationTests:
    LOGIN_FAIL_TEXT = "Your login attempt was not successful, try again."
    OVERTIME_WARNING_TEXT = "You already have 10 hours of work for the specified date and your manager doesn't allow you overtime.";

    def __init__(self, root="http://127.0.0.1:8082") -> None:
        self.driver = webdriver.Chrome()
        self.root = root
    
    def __del__(self):
        self.driver.close()

    def login(self, isAdmin, success):
        self.driver.get("{}/login".format(self.root))

        usernameField = self.driver.find_element(By.NAME, "username")
        passwordField = self.driver.find_element(By.NAME, "password")

        if isAdmin:
            usernameField.send_keys("DemoAdmin")
        else:
            usernameField.send_keys("DemoUser1")
        
        if success:
            passwordField.send_keys("password")
        else:
            passwordField.send_keys("wrongpassword")
        
        self.driver.find_element(By.NAME, "submit").click()
    
    def overtime(self, enable):
        self.login(True, True)

        if enable:
            self.driver.find_element(By.ID, "overtime-on").click()
        else:
            self.driver.find_element(By.ID, "overtime-off").click()

        self.driver.find_element(By.ID, "logout").click()
    
    def addTodo(self):
        self.login(False, True)
        self.driver.find_element(By.ID, "todos").click()
        self.driver.find_element(By.ID, "add-todo-btn").click()

        hoursField = self.driver.find_element(By.ID, "hoursRequired")
        hoursField.clear()
        hoursField.send_keys("50")

        self.driver.find_element(By.ID, "add-todo-btn-submit").click()

    def testLoginSuccess(self):
        self.login(False, True)
        assert self.driver.find_element(By.ID, "logout").is_displayed()
    
    def testLoginFailure(self):
        self.login(False, False)
        assert SpringBootFirstWebApplicationTests.LOGIN_FAIL_TEXT in self.driver.page_source
    
    def testEnableOvertime(self):
        self.overtime(True)
        self.addTodo()

        assert SpringBootFirstWebApplicationTests.OVERTIME_WARNING_TEXT not in self.driver.page_source
    
    def testDisableOvertime(self):
        self.overtime(False)
        self.addTodo()

        assert SpringBootFirstWebApplicationTests.OVERTIME_WARNING_TEXT in self.driver.page_source

if __name__ == "__main__":
    testRunner = SpringBootFirstWebApplicationTests()
    testRunner.testLoginSuccess()
    testRunner.testLoginFailure()
    testRunner.testEnableOvertime()
    testRunner.testDisableOvertime()
