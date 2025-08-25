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
	
	@Test(priority = 5)
	public void browserWindowsNewWindowMsg() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Window Message...");
		testRep.info("Starting test for Alerts, Frames & Windows|Browser Windows|New Window Message");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|New Window Message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		alertsframewindowsPage.clickNewWindowMessage();
		testRep.info("Test  Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
	}
	
	@Test(priority = 6)
	public void ClickAlerts() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts...");
		testRep.info("Starting test for Alerts, Frames & Windows|Alerts");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|New Window Message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		testRep.info("Test  Alerts, Frames & Windows|Alerts Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Alerts Test Completed...");
	}
	
	@Test(priority = 7)
	public void ClickToSeeAlerts() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|Click button to see Alert...");
		testRep.info("Starting test for Alerts, Frames & Windows|Alerts|Click button to see Alert");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|Alerts|Click button to see Alert...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		alertsframewindowsPage.clickSeeAlert5sec();
		testRep.info("Test  Alerts, Frames & Windows|Alerts|Click button to see Alert Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Alerts|Click button to see Alert Test Completed...");
	}
	
	@Test(priority = 8)
	public void ClickAcceptConfirmationBox() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept...");
		testRep.info("Starting test for Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|AlertsOn button click, confirm box will appear, Accept...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		alertsframewindowsPage.clickConfirmBoxAlertAccept();
		testRep.info("Test  Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept Test Completed...");
	}
	
	@Test(priority = 8)
	public void ClickDenyConfirmationBox() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny...");
		testRep.info("Starting test for Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		alertsframewindowsPage.clickConfirmBoxAlertDeny();
		testRep.info("Test  Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny Test Completed...");
	}
	
	@Test(priority = 9)
	public void ClickPromptBoxFill() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data...");
		testRep.info("Starting test for Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data");
		demoqaLog.info("Starting Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		alertsframewindowsPage.clickPromptFill();
		testRep.info("Test  Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data Test Completed...");
	}
	
}
