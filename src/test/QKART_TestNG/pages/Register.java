package QKART_TestNG.pages;

import java.sql.Timestamp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

public class Register {
    public static final String lastGeneratedUsername = null;

    // call remotewebdriver as driver
    RemoteWebDriver driver;

    // url of register page
    String url = "https://crio-qkart-frontend-qa.vercel.app/register";

    //page factory
    @FindBy(id = "username")
    WebElement usernameTextBox;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordTextBox;

    @FindBy(className = "button")
    WebElement registerNowButton;


    public Register(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
    }

    Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

    public void navigateToRegisterPage() {
        // navigate to register page
        if (!this.driver.getCurrentUrl()
                .equals("https://crio-qkart-frontend-qa.vercel.app/register")) {
            this.driver.get(url);
        }
    }

    public Boolean registerUser(String username, String password)
            throws InterruptedException {
        // if (usernameDynamic)

        //     username = username + String.valueOf(timeStamp.getTime());

        String testPassword = password;

        // WebElement usernameTextBox = driver.findElement(By.id("username"));
        // WebElement passwordTextBox = driver.findElement(By.id("password"));
        // WebElement confirmPasswordTextBox = driver.findElement(By.id("confirmPassword"));
        // WebElement registerNowButton = driver.findElement(By.className("button"));

        usernameTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        confirmPasswordTextBox.sendKeys(password);
        registerNowButton.click();
        Thread.sleep(3000);
        // this.lastGeneratedUsername = username;

        return true;

    }
}
