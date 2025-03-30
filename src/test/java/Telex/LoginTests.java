package Telex;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTests {
    private static AndroidDriver driver;

    ExtentSparkReporter spark = new ExtentSparkReporter("src/test/java/Reports/telex_report.html");
    ExtentReports extent;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        extent = new ExtentReports();
        extent.attachReporter(spark);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "14");
        caps.setCapability("deviceName", "Redmi14C");
        caps.setCapability("appPackage", "com.telex.app");
        caps.setCapability("appActivity", "com.telex.app.MainActivity");
        caps.setCapability("udid", "AI7T9PJ7K7T49HXK");
        caps.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

    }

    @Test(priority = 1)
    public void testLoginWithMagicLink() {
        ExtentTest test = extent.createTest("Login with Magic link").assignCategory("Login");

        System.out.println("Running Login with Magic Link Test");

        // Enter email address
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("jemimaitulua@gmail.com");

        // Click "Login with Magic Link" button
        driver.findElement(By.id("com.telex.app:id/magic_link_button")).click();

        // Verify magic link confirmation message is displayed

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/magic_link_sent_message")).isDisplayed(),
                "Magic link was not sent successfully"
        );

        System.out.println("Magic Link sent successfully!");


    }

    @Test(priority = 2)
    public void testEmptyEmailAndPassword() {
        ExtentTest test = extent.createTest("Login with empty email and password").assignCategory("Login");
        System.out.println("Running Empty Email & Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();


        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
        test.pass("Test passed");

    }

    @Test(priority = 3)
    public void testInvalidEmailFormat() {
        ExtentTest test = extent.createTest("Login with invalid email format").assignCategory("Login");
        System.out.println("Running Invalid Email Format Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("invalidEmail");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
        test.pass("Test passed");

    }

    @Test(priority = 4)
    public void testIncorrectPassword() {
        extent.createTest("Login with incorrect password").assignCategory("Login");
        System.out.println("Running Incorrect Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("testuser@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("WrongPassword123");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test(priority = 5)
    public void testNonExistentAccount() {
        extent.createTest("Login with invalid non existence account").assignCategory("Login");
        System.out.println("Running Non-Existent Account Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("fakeuser@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test(priority = 6)
    public void testValidEmailEmptyPassword() {
        extent.createTest("Login with valid email and empty password").assignCategory("Login");
        System.out.println("Running Valid Email but Empty Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("testuser@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test(priority = 7)
    public void testValidPasswordEmptyEmail() {
        extent.createTest("Login with invalid password and empty email").assignCategory("Login");
        System.out.println("Running Valid Password but Empty Email Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test(priority = 8)
    public void testSpecialCharactersInEmail() {
        extent.createTest("Login with special character in the email").assignCategory("Login");
        System.out.println("Running Special Characters in Email Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("user!@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test(priority = 9)
    public void testWhitespaceInEmailOrPassword() {
        extent.createTest("Login with white space in email and password").assignCategory("Login");
        System.out.println("Running Whitespace in Email or Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys(" testuser@example.com ");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys(" Password@2 ");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test(priority = 10)
    public void testCaseSensitivityInEmailAndPassword() {
        extent.createTest("Case sensitivity on email and password").assignCategory("Login");
        System.out.println("Running Case Sensitivity Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("TestUser@Example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("PASSWORD@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Inside the tear down method");
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }

}



