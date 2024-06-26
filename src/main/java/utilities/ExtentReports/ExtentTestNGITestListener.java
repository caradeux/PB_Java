package utilities.ExtentReports;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.testng.ITestNGListener;
import ambientes.AbstractBase;
import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReports.ExtentManager;
import utilities.ExtentReports.ExtentTestManager;
import utilities.log.Log;

import java.io.File;
import java.io.IOException;


public class ExtentTestNGITestListener extends AbstractBase implements ITestListener {

    String workingDir = System.getProperty("user.dir");
    String filepath = workingDir+"\\Reporte\\ScreenShots\\";

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
        //Start operation for extentreports.
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        //Extentreports log operation for passed tests.
        Object testClass = iTestResult.getInstance();

        //Get driver from BaseTest and assign to local webdriver variable.
        driver = ((AbstractBase) testClass).getDriver();

        //Allure ScreenShotRobot and SaveTestLog
        if (driver instanceof WebDriver) {
            //System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " OK and screenshot taken!");

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.BASE64);

        //Extentreports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test Pass",ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));

    }



    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();

        //Get driver from BaseTest and assign to local webdriver variable.
        driver = ((AbstractBase) testClass).getDriver();

        //Allure ScreenShotRobot and SaveTestLog
        if (driver instanceof WebDriver) {
           // System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

        //Extentreports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
         ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));

    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        //Extentreports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}
