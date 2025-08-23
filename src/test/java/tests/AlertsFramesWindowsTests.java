package tests;

import org.testng.annotations.Test;

import base.demoqaBase;
import pages.alertsframewindowsPage;
//import utils.demoqaLog;
import utils.extentReportManager;

public class AlertsFramesWindowsTests extends demoqaBase {

	@Test(priority = 1)
	public void accessAlertsFrameWindows() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows...");
		testRep.info("Starting test for Alerts, Frames & Windows");
		demoqaLog.info("Starting Test Alerts, Frames & Windows...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		testRep.info("Test  Alerts, Frames & Windows Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows Test Completed...");
	}

	@Test(priority = 2)
	public void browserWindows() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows...");
		testRep.info("Starting test for Alerts, Frames & Windows|Browser Windows");
		demoqaLog.info("Starting Test Alerts, Frames & Windows...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		testRep.info("Test  Alerts, Frames & Windows|Browser Windows Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Browser Windows Test Completed...");
	}
	
	@Test(priority = 3)
	public void browserWindowsNewTab() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Tab...");
		testRep.info("Starting test for Alerts, Frames & Windows|Browser Windows|New Tab");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|New Tab...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		alertsframewindowsPage.clickNewTab();
		testRep.info("Test  Alerts, Frames & Windows|Browser Windows|New Tab Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Browser Windows|New Tab Test Completed...");
	}
	
	@Test(priority = 4)
	public void browserWindowsNewWindow() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Window...");
		testRep.info("Starting test for Alerts, Frames & Windows|Browser Windows|New Window");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|New Window...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		alertsframewindowsPage.clickNewWindow();
		testRep.info("Test  Alerts, Frames & Windows|Browser Windows|New Window Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Browser Windows|New Window Test Completed...");
	}
	
//	@Test(priority = 5) - This in Progress hitting time out issue
//	public void browserWindowsNewWindowMsg() {
//		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Window Message...");
//		testRep.info("Starting test for Alerts, Frames & Windows|Browser Windows|New Window Message");
//		demoqaLog.info("Starting Test Alerts, Frames & Windows|New Window Message...");
//		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
//		alertsframewindowsPage.accessAlertFramesWindows();
//		alertsframewindowsPage.clickBrowserWindow();
//		alertsframewindowsPage.clickNewWindowMessage();
//		testRep.info("Test  Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
//		demoqaLog.info(" Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
//	}
}
