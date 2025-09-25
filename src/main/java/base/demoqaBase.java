package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import customAnnotations.CaptureOnSuccess;
import utils.ChromeProfileCleaner;
import utils.ConfigLoader;
import utils.DriverFactory;
import utils.PauseManager;
import utils.RetryUrlAccess;
import utils.emailUtils;
import utils.extentReportManager;

public class demoqaBase {

    protected WebDriver driver;
    protected static ExtentReports extentRep;
    protected ExtentTest testRep;
    protected static final Logger demoqaLog = LogManager.getLogger(demoqaBase.class);
    protected static final String baseUrl = "https://demoqa.com";
    protected static ExtentTest systemEventTest;
    private static final AtomicInteger testCounter = new AtomicInteger(0);
    protected static final boolean CONTINUE_ON_FAILURE = ConfigLoader.getBoolean("continueOnFailure", true);

    @BeforeSuite
    public static void setupReport() {
        extentRep = extentReportManager.getReportInstance();
        systemEventTest = extentRep.createTest("SYSTEM - Events");
        systemEventTest.info("System event logging started");
    }
    
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {
        try {
            driver = DriverFactory.createDriver();

            // IMPORTANT: assign back the possibly new driver
            driver = RetryUrlAccess.navigateWithRetry(driver, baseUrl, 3);

            if (driver == null) {
                demoqaLog.error("❌ Setup failed: could not reach {}", baseUrl);
                throw new SkipException("Skipping test " + method.getName() + " due to navigation failure");
            }

            testRep = extentRep.createTest(method.getName());
        } catch (Exception e) {
            demoqaLog.error("❌ Setup failed: ", e);
            throw new RuntimeException("Setup failed", e);
        }
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        boolean testFailed = result.getStatus() == ITestResult.FAILURE;
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String testName = result.getName();

        try {
            if (testFailed) {
                String screenshotPath = extentReportManager.captureScreenShot(driver, testName + "_Failed_" + timestamp);
                if (testRep != null) {
                    testRep.fail("❌ Test Failed",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
                demoqaLog.error("❌ Test {} failed. Screenshot captured.", testName);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                Method method = result.getMethod().getConstructorOrMethod().getMethod();
                if (method.isAnnotationPresent(CaptureOnSuccess.class)) {
                    CaptureOnSuccess meta = method.getAnnotation(CaptureOnSuccess.class);
                    String description = meta.description();
                    String screenshotPath = extentReportManager.captureScreenShot(driver, testName + "_Passed_" + timestamp);
                    if (testRep != null) {
                        testRep.pass("✅ " + description,
                                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    }
                    demoqaLog.info("✅ Test {} passed. Screenshot captured.", testName);
                }
            }
        } finally {
            try {
                if (driver != null) {
                    demoqaLog.info("Closing browser for test: {}", testName);
                    driver.quit();
                }
            } catch (Exception ex) {
                demoqaLog.warn("Exception while quitting driver: {}", ex.getMessage());
            }
        }

        if (testFailed && !CONTINUE_ON_FAILURE) {
            demoqaLog.warn("continueOnFailure=false, stopping execution after failure of test: {}", testName);
            throw new RuntimeException("Stopping suite due to test failure: " + testName, result.getThrowable());
        }

        PauseManager.handlePause(testCounter.incrementAndGet(), systemEventTest);
    }

    @AfterSuite(alwaysRun = true)
    public void teardownSuite() {
        try {
            if (extentRep != null) {
                extentRep.flush();
                File fullPath = new File(extentReportManager.reportPath);
                String reportFolder = fullPath.getParent();
                demoqaLog.info("📊 Extent report generated at: {}", reportFolder);

                try {
//                    emailUtils.sendTestReport(reportFolder);
                } catch (Exception e) {
                    demoqaLog.warn("⚠️ Email sending failed: {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            demoqaLog.error("❌ Failed to flush extent report", e);
        }

        try {
            Path tempProfileDir = DriverFactory.getTempProfileDir();
            if (tempProfileDir != null) {
                FileUtils.deleteDirectory(tempProfileDir.toFile());
                demoqaLog.info("🧹 Deleted temp Chrome profile: {}", tempProfileDir);
            }
            ChromeProfileCleaner.cleanChromeProfiles();
            demoqaLog.info("🧹 Cleaned up all stale Chrome profiles");
        } catch (IOException e) {
            demoqaLog.warn("⚠️ Failed to clean Chrome profiles", e);
        }
    }
}