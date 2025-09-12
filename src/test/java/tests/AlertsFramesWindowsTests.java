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
		testRep.info("🧪 Starting test for Alerts, Frames & Windows");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		testRep.pass("✅ Test Alerts, Frames & Windows Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows Test Completed...");
	}

	@Test(priority = 2)
	public void browserWindows() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows Test Completed...");
	}
	
	@Test(priority = 3)
	public void browserWindowsNewTab() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Tab...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows|New Tab");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Tab...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		alertsframewindowsPage.clickNewTab();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows|New Tab Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows|New Tab Test Completed...");
	}
	
	@Test(priority = 4)
	public void browserWindowsNewWindow() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Window...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows|New Window");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Window...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		alertsframewindowsPage.clickNewWindow();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows|New Window Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows|New Window Test Completed...");
	}
	
	@Test(priority = 5)
	public void browserWindowsNewWindowMsg() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Window Message...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows|New Window Message");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Window Message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		alertsframewindowsPage.clickNewWindowMessage();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
	}
	
	@Test(priority = 6)
	public void ClickAlerts() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Alerts");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Window Message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Alerts Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Alerts Test Completed...");
	}
	
	@Test(priority = 7)
	public void ClickToSeeAlerts() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|Click button to see Alert...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Alerts|Click button to see Alert");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Alerts|Click button to see Alert...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.clickSeeAlert5sec();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Alerts|Click button to see Alert Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Alerts|Click button to see Alert Test Completed...");
	}
	
	@Test(priority = 8)
	public void ClickAcceptConfirmationBox() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|AlertsOn button click, confirm box will appear, Accept...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.clickConfirmBoxAlertAccept();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept Test Completed...");
	}
	
	@Test(priority = 8)
	public void ClickDenyConfirmationBox() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.clickConfirmBoxAlertDeny();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny Test Completed...");
	}
	
	@Test(priority = 9)
	public void ClickPromptBoxFill() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.clickPromptFill();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data Test Completed...");
	}
	
	@Test(priority = 10)
	public void ClickFrames() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickFrames();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Frames Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Frames Test Completed...");
	}
	
	@Test(priority = 11)
	public void GetFrame1() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Frame1 message...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Frame1 message");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Frame1 message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickFrames();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.GetFrame1msg();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Frame1 message Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Frame1 message Test Completed...");
	}
	
	@Test(priority = 12)
	public void GetFrame2() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Frame2 message...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Frame2 message");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Frame2 message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickFrames();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.GetFrame2msg();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Frame2 message Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Frame2 message Test Completed...");
	}
	
	@Test(priority = 13)
	public void ClickNestedFrames() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Nested Frames...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Nested Frames");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Nested Frames...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.clickNestedFrames();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Nested Frames Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Nested Frames Test Completed...");
	}
	
	@Test(priority = 14)
	public void GetParentFrame() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Parent Frame message...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Parent Frame message");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Parent Frame message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickNestedFrames();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.GetParentFramemsg();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Frames|Parent Frame message Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Parent Frame message Test Completed...");
	}
	
	@Test(priority = 15)
	public void GetChildFrame() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Child Frame message...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Child Frame message");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Child Frame message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickNestedFrames();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.GetChildFramemsg();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Frames|Child Frame message Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Child Frame message Test Completed...");
	}
	
	@Test(priority = 13)
	public void ClickModalDialogs() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Modal Dialogs...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Modal Dialogs");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Modal Dialogs...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickModalDialogs();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Modal Dialogs Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Modal Dialogs Test Completed...");
	}
	
	@Test(priority = 14)
	public void ClickSmallModal() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Modal Dialogs|Small Modal click...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Modal Dialogs|Small Modal click");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Modal Dialogs|Small Modal...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickModalDialogs();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.clickSmallModal();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Modal Dialogs|Small Modal click Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Modal Dialogs|Small Modal click Test Completed...");
	}
	
	@Test(priority = 14)
	public void ClickLargeModal() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Modal Dialogs|Large Modal click...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Modal Dialogs|Large Modal click");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Modal Dialogs|Large Modal...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickModalDialogs();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertsframewindowsPage.clickLargeModal();
		
		testRep.pass("✅ Test Alerts, Frames & Windows|Modal Dialogs|Large Modal click Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Modal Dialogs|Large Modal click Test Completed...");
	}
}
