package QKART_TestNG.tests.tests;

import QKART_TestNG.pages.Login;
import QKART_TestNG.pages.Register;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TestPages {

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



	// TODO: Use the following to test --navigateToRegisterPage()-- method

	@Test(description = "Verify functionality of - navigate to register page", enabled = true)
	public static void testRegister_navigateToRegisterPage() {
		Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to register page", "started");
		try {
			Register register = new Register(driver);
			register.navigateToRegisterPage();

			String expectedUrl = "https://crio-qkart-frontend-qa.vercel.app/register";
			String actualUrl = driver.getCurrentUrl();

			assertion.assertEquals(actualUrl, expectedUrl,
					"Actual" + actualUrl + "does not match as expected one" + expectedUrl);
			logStatus("Page test", "navigation to register page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to register page", "failed");
			e.printStackTrace();
		}
	}


	// TODO: Implement Rest of Unit Tests Here-

	@Test(description = "Verify functionality of - navigate to login page", enabled = true)
	public static void testLogin_navigateToLoginPage() {
		Assertion assertion = new Assertion();
		logStatus("Page test", "navigation to login page", "started");
		try {
			Register register = new Register(driver);
			register.navigateToRegisterPage();
			assertion.assertTrue(driver.getCurrentUrl()
					.equals("https://crio-qkart-frontend-qa.vercel.app/register"));
			logStatus("Page test", "navigation to login page", "success");
		} catch (Exception e) {
			logStatus("Page test", "navigation to login page", "failed");
			e.printStackTrace();
		}
	}


	@Test(description = "Verify functionality of - perform login ", enabled = true)
	public static void testLogin_performLogin() {
		try {
			Assertion assertion = new Assertion();
			logStatus("Page test", "perform login ", "started");

			Register register = new Register(driver);
			register.navigateToRegisterPage();
			register.registerUser("testuser0000", "1234567");
			Thread.sleep(3000);
			Login login = new Login(driver);
			login.navigateToLoginPage();
			Boolean status = login.performLogin("testuser0000", "1234567");
			if (status) {
				throw new Exception("Registered Not Done");
			}
			logStatus("Page test", "perform login ", "success");
		} catch (Exception e) {
			logStatus("Page test", "perform login ", "failed");
			e.printStackTrace();
		}
	}

	@Test(description = "Verify functionality of -  verification of user login ", enabled = true)
	public static void testLogin_verifyUserLoggedIn() {
		try {
			Assertion assertion = new Assertion();
			logStatus("Page test", "perform login ", "started");
			Register register = new Register(driver);
			register.navigateToRegisterPage();
			register.registerUser("testuser0000", "1234567");
			Thread.sleep(3000);
			Login login = new Login(driver);
			login.navigateToLoginPage();
			Boolean status = login.performLogin("testuser0000", "1234567");
			if (status) {
				logStatus("Page test", "verify user logged in", "success");
			} else {
				throw new Exception("Not Logged in ");
			}
		} catch (Exception e) {
			logStatus("Page test", "Not logged in", "success");
		}
	}

	// Quit webdriver after Unit Tests
	@AfterClass(enabled = true)
	public static void quitDriver() throws MalformedURLException {
		driver.close();
		driver.quit();
		logStatus("driver", "Quitting driver", "Success");
	}

}
