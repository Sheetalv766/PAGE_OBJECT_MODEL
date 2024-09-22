package QKART_TestNG.tests.tests;

import QKART_TestNG.pages.Login;
import QKART_TestNG.pages.Register;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase {
    static RemoteWebDriver driver;

	// Method to help us log our Unit Tests
	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

	// Iinitialize webdriver for our Unit Tests
	@BeforeSuite
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		logStatus("driver", "Initializing driver- Testcase", "Success");
	}
    

    @Test(description = "Testing user registration and login", enabled = true)
    public void testCase(String usernameNameUnique) throws InterruptedException{
        Register register = new Register(driver);
        register.navigateToRegisterPage();
        register.registerUser("username123", "1234567abc");
        Assert.assertTrue(register.registerUser("username123", "1234567abc"));
        String usernameName = register.lastGeneratedUsername;
        Login login = new Login(driver);
        login.navigateToLoginPage();
        login.performLogin(usernameNameUnique, "1234567abc");
        Assert.assertTrue(login.verifyUserLoggedIn(usernameNameUnique));
    }
}

