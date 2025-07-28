package tests;

import org.testng.annotations.Test;

import base.demoqaBase;
import pages.alertsframewindowsPage;
import utils.demoqaLog;
import utils.extentReportManager;

public class AlertsFramesWindowsTests extends demoqaBase{
	
	@Test(priority=1)
	public void accessAlertsFrameWindows() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows...");
		testRep.info("Starting test for Alerts, Frames & Windows");
		demoqaLog.info("Starting Test Alerts, Frames & Windows...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		testRep.info("Test  Alerts, Frames & Windows Test Completed...");
		demoqaLog.info(" Alerts, Frames & Windows Test Completed...");
	}

}
