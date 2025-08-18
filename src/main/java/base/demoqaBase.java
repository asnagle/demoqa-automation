package base;

import java.io.File;
//import java.io.File;
//import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import customAnnotations.CaptureOnSuccess;
import utils.emailUtils;
import utils.extentReportManager;
import utils.retryUrlAccess;


public class demoqaBase {
	
	protected WebDriver driver;
	protected static ExtentReports extentRep;
	protected ExtentTest testRep;
	private static final Logger demoqaLog = LogManager.getLogger(demoqaBase.class);

	
	@BeforeSuite
	public static void setupReport() {

		extentRep = extentReportManager.getReportInstance();
	}
	
	@AfterSuite
	public static void teardownReport() {
		try {
			extentRep.flush();

//			***** Uncomment to enable emailing report feature
			File fullPath = new File(extentReportManager.reportPath);
			String reportFolder = fullPath.getParent();
//			emailUtils.sendTestReport(reportFolder);
		} catch (Exception e) {
			System.err.println("Flush failed: " + e.getMessage());
		}
	}

	@BeforeMethod
	public void setUP () {
		
		demoqaLog.info("Starting Web Browser...");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");  // ← This is the key!
		options.addArguments("--start-maximized");
//		options.addArguments("--disable-blink-features=AutomationControlled");
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
				+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
		options.addArguments("--disable-application-cache");
		options.addArguments("--disk-cache-size=0");
		options.addArguments("user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID());
		driver = new ChromeDriver(options);
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		    driver.manage().deleteAllCookies();
		    ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
		    ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");


		
		demoqaLog.info("Navigating to demoqa.com Website");
//		driver.get("https://demoqa.com/");
		retryUrlAccess.navigateWithRetry(driver, "https://demoqa.com", 3);

//		
//		=================== For Using Edge Browser =====================
		
//		EdgeOptions options = new EdgeOptions();
//		 options.addArguments("--start-maximized");
//		 options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
//		 driver = new EdgeDriver(options);
//		 driver.get("https://demoqa.com/");
		
	}
	
	@AfterMethod
	public void TearDown(ITestResult result) {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = extentReportManager.captureScreenShot(driver, testRep +"Test Failed");
			testRep.fail("Test Failed... Check Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		
//		=============== Capturing screen of a particular test case if passed (Using Custom Annotations)
		Method method = result.getMethod().getConstructorOrMethod().getMethod();

		if (method.isAnnotationPresent(CaptureOnSuccess.class)) {
		    CaptureOnSuccess meta = method.getAnnotation(CaptureOnSuccess.class);

		    String description = meta.description();
//		    String mode = meta.screenshotMode(); // "full" or "viewport" etc.

		    String screenshotPath = extentReportManager.captureScreenShot(driver, method.getName() + " Passed");

		    testRep.pass(description + " — Screenshot captured",
		        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		
//		===============================================================================		
		if (driver != null) {
			demoqaLog.info("Closing the Browser...");
			driver.quit();
		}
	}

}
