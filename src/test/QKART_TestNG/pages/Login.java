package QKART_TestNG.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Login {

    // call remotewebdriver as driver
    RemoteWebDriver driver;

    // page factory
    @FindBy(id = "username")
    WebElement usernameTextBox;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(className = "button")
    WebElement loginButton;

    @FindBy(className = "username-text")
    WebElement usernameLabel;

    // initialize the driver
    public Login(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }


    public Boolean navigateToLoginPage() {
        return true;
    }

    public Boolean performLogin(String username, String password) throws InterruptedException {
        // WebElement usernameTextBox = driver.findElement(By.id("username"));
        // WebElement passwordTextBox = driver.findElement(By.id("password"));
        // WebElement loginButton = driver.findElement(By.className("button"));

        System.out.println("Login username : " + username + "Enter password : " + password);

        // enter the username
        usernameTextBox.sendKeys(username);

        // enter the password
        passwordTextBox.sendKeys(password);

        // click on the login button
        loginButton.click();
        Thread.sleep(3000);

        return this.verifyUserLoggedIn(username);
    }

    public Boolean verifyUserLoggedIn(String username) {
        try {
            // WebElement usernameLabel = driver.findElement(By.className("username-text"));
            return usernameLabel.getText().equals(username);
        } catch (Exception e) {
            return false;
        }
    }
}
