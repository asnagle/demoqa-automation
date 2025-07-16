package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportManager {
	
	private static ExtentReports extentRep;
	private static ExtentTest testRep;

	public static String reportPath;

	public static ExtentReports getReportInstance() {

		if (extentRep == null) {

			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			reportPath = "reports/ExtentReport_" + timestamp + ".html";

			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setDocumentTitle("Test Report of demoqa.com site");
			reporter.config().setReportName("Automated Test Execution Report");

			extentRep = new ExtentReports();
			extentRep.attachReporter(reporter);

		}
		return extentRep;
	}

	public static ExtentTest createTest(String testName) {

		testRep = getReportInstance().createTest(testName);
		return testRep;

	}

	public static String captureScreenShot(WebDriver driver, String screenshotName) {

		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "+" + timestamp + ".png";
			System.out.println("Path for Screenshot is: " + path);
			FileUtils.copyFile(src, new File(path));
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}


