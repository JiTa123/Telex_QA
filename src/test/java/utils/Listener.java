package utils;

import Telex.LoginTests;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;


public class Listener extends LoginTests implements ITestListener {

    private final Logger Log = LogManager.getLogger(Listener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("{} test has succeeded.", getTestMethodName(result));
        LoginTests.test.log(Status.PASS, "Test has passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = getTestMethodName(result);
        Log.error("{} test has failed.", testName);

        String failedScreenShot = "data:image/png;base64," +
                ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        LoginTests.test.log(Status.FAIL, "Test has failed. See details below.")
                .addScreenCaptureFromBase64String(failedScreenShot, "Screenshot on Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onStart(ITestContext context) {
        Log.info("{} tests are starting", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("{} tests are ending", context.getName());
        LoginTests.extent.flush();
    }
}
