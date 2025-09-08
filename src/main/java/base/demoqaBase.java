package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import customAnnotations.CaptureOnSuccess;
import utils.ChromeProfileCleaner;
import utils.DriverFactory;
import utils.RetryUrlAccess;
import utils.emailUtils;
import utils.extentReportManager;

public class demoqaBase {

    protected WebDriver driver;
    protected static ExtentReports extentRep;
    protected ExtentTest testRep;
    protected static final Logger demoqaLog = LogManager.getLogger(demoqaBase.class);
    protected static final String baseUrl = "https://demoqa.com";

    @BeforeSuite
    public static void setupReport() {
        extentRep = extentReportManager.getReportInstance();
    }

    @BeforeMethod
    public void setup(Method method) {
        driver = DriverFactory.createDriver();
        boolean navigated = false;

        driver = RetryUrlAccess.navigateWithRetry(driver, baseUrl, 3);
        if (driver != null) navigated = true;

        if (!navigated) {
            demoqaLog.error("❌ Could not navigate to {} after retries", baseUrl);
            if (driver != null) driver.quit();
            Assert.fail("Failed to navigate to base URL after retries");
        }

        testRep = extentRep.createTest(method.getName());
        demoqaLog.info("🚀 Starting test: {}", method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = extentReportManager.captureScreenShot(driver, result.getName() + "_Failed");
                testRep.fail("❌ Test Failed",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                demoqaLog.error("❌ Test {} failed. Screenshot captured.", result.getName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                Method method = result.getMethod().getConstructorOrMethod().getMethod();
                if (method.isAnnotationPresent(CaptureOnSuccess.class)) {
                    CaptureOnSuccess meta = method.getAnnotation(CaptureOnSuccess.class);
                    String description = meta.description();
                    String screenshotPath = extentReportManager.captureScreenShot(driver, result.getName() + "_Passed");
                    testRep.pass("✅ " + description,
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    demoqaLog.info("✅ Test {} passed. Screenshot captured.", result.getName());
                }
            }
        } finally {
            if (driver != null) {
                demoqaLog.info("Closing the Browser for test: {}", result.getName());
                driver.quit();
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void teardownSuite() {
        try {
            if (extentRep != null) {
                extentRep.flush();
                File fullPath = new File(extentReportManager.reportPath);
                String reportFolder = fullPath.getParent();
                demoqaLog.info("📊 Extent report generated at: {}", reportFolder);

                // Email report if SMTP configured
                try {
//                    emailUtils.sendTestReport(reportFolder);
                } catch (Exception e) {
                    demoqaLog.warn("⚠️ Email sending failed: {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            demoqaLog.error("❌ Failed to flush extent report", e);
        }

        // Clean up Chrome profiles
        try {
            Path tempProfileDir = DriverFactory.getTempProfileDir();
            if (tempProfileDir != null) {
                FileUtils.deleteDirectory(tempProfileDir.toFile());
                demoqaLog.info("🧹 Deleted temp Chrome profile: {}", tempProfileDir);
            }
            ChromeProfileCleaner.cleanChromeProfiles();
            demoqaLog.info("🧹 Cleaned up all stale Chrome profiles after suite execution");
        } catch (IOException e) {
            demoqaLog.warn("⚠️ Failed to clean Chrome profiles", e);
        }
    }
}
