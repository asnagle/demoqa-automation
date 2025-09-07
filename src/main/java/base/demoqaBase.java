
package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import customAnnotations.CaptureOnSuccess;
import utils.RetryUrlAccess;
import utils.emailUtils;
import utils.extentReportManager;

public class demoqaBase {

    protected WebDriver driver;
    protected static ExtentReports extentRep;
    protected ExtentTest testRep;
    protected static final Logger demoqaLog = LogManager.getLogger(demoqaBase.class);
    protected static Path tempProfileDir;

    @BeforeSuite
    public static void setupReport() {
        extentRep = extentReportManager.getReportInstance();
    }

    @AfterSuite
    public static void teardownReport() {
        try {
            extentRep.flush();
            File fullPath = new File(extentReportManager.reportPath);
            String reportFolder = fullPath.getParent();
//            emailUtils.sendTestReport(reportFolder);
        } catch (Exception e) {
            System.err.println("Flush failed: " + e.getMessage());
        }

        // Clean up Chrome profile directory
        if (tempProfileDir != null) {
            try {
                FileUtils.deleteDirectory(tempProfileDir.toFile());
                demoqaLog.info("🧹 Deleted temp Chrome profile: {}", tempProfileDir);
            } catch (IOException e) {
                demoqaLog.warn("⚠️ Failed to delete temp profile: {}", tempProfileDir, e);
            }
        }
    }

    @BeforeMethod
    public void setUP() {
        demoqaLog.info("Starting Web Browser...");

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // safer for page validation
        options.addArguments("--start-maximized");
        options.addArguments("--disable-application-cache");
        options.addArguments("--disk-cache-size=0");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        try {
            tempProfileDir = Files.createTempDirectory("chrome-profile-");
            options.addArguments("user-data-dir=" + tempProfileDir.toAbsolutePath());
        } catch (IOException e) {
            demoqaLog.error("❌ Failed to create temp Chrome profile directory", e);
        }

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");

        demoqaLog.info("Navigating to demoqa.com Website");
        RetryUrlAccess.navigateWithRetry(driver, "https://demoqa.com", 3);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = extentReportManager.captureScreenShot(driver, testRep + "Test Failed");
            testRep.fail("Test Failed... Check Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(CaptureOnSuccess.class)) {
            CaptureOnSuccess meta = method.getAnnotation(CaptureOnSuccess.class);
            String description = meta.description();
            String screenshotPath = extentReportManager.captureScreenShot(driver, method.getName() + " Passed");
            testRep.pass(description + " — Screenshot captured",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        if (driver != null) {
            demoqaLog.info("Closing the Browser...");
            driver.quit();
        }
    }
}
