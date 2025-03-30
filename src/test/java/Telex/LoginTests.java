package Telex;

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

    @BeforeClass
    public static void setUp() throws MalformedURLException {
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

    @Test
    public void testLoginWithMagicLink() {
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
    @Test
    public void testEmptyEmailAndPassword () {
        System.out.println("Running Empty Email & Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testInvalidEmailFormat () {
        System.out.println("Running Invalid Email Format Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("invalidEmail");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testIncorrectPassword () {
        System.out.println("Running Incorrect Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("testuser@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("WrongPassword123");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testNonExistentAccount () {
        System.out.println("Running Non-Existent Account Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("fakeuser@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testValidEmailEmptyPassword () {
        System.out.println("Running Valid Email but Empty Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("testuser@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testValidPasswordEmptyEmail () {
        System.out.println("Running Valid Password but Empty Email Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testSpecialCharactersInEmail () {
        System.out.println("Running Special Characters in Email Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("user!@example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("Password@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testWhitespaceInEmailOrPassword () {
        System.out.println("Running Whitespace in Email or Password Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys(" testuser@example.com ");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys(" Password@2 ");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }

    @Test
    public void testCaseSensitivityInEmailAndPassword () {
        System.out.println("Running Case Sensitivity Test");
        driver.findElement(By.id("com.telex.app:id/emailaddress")).sendKeys("TestUser@Example.com");
        driver.findElement(By.id("com.telex.app:id/password")).sendKeys("PASSWORD@2");
        driver.findElement(By.id("com.telex.app:id/login_button")).click();

        Assert.assertTrue(
                driver.findElement(By.id("com.telex.app:id/error_message")).isDisplayed()
        );
    }
    @AfterClass
    public static void tearDown () {
        if (driver != null) {
            driver.quit();
        }
    }

}



