package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.demoqaBase;
import pages.alertsframewindowsPage;
import utils.PageLoadHandler;
//import utils.demoqaLog;
import utils.extentReportManager;
import utils.waitForElement;

public class AlertsFramesWindowsTests extends demoqaBase {

	@Test(priority = 1, description = "Test Alerts, Frames & Windows...")
	public void accessAlertsFrameWindows() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		String cardpage = alertsframewindowsPage.accessAlertFramesWindows();

		Assert.assertEquals("Please select an item from left to start practice.", cardpage);

		testRep.pass("✅ Assertion Confirmatin Text: " + cardpage);
		testRep.pass("✅ Test Alerts, Frames & Windows Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows Test Completed...");
	}

	@Test(priority = 2, description = "Test Alerts, Frames & Windows|Browser Windows...")
	public void browserWindows() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String browserWindowsPage = alertsframewindowsPage.clickBrowserWindow();

		Assert.assertEquals("Browser Windows", browserWindowsPage);

		testRep.pass("✅ Assertion Confirmatin Text: " + browserWindowsPage);
		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows Test Completed...");
	}

	@Test(priority = 3, description = "Test Alerts, Frames & Windows|Browser Windows|New Tab...")
	public void browserWindowsNewTab() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Tab...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows|New Tab");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Tab...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String result = alertsframewindowsPage.clickNewTab();

		Assert.assertEquals("This is a sample page", result);

		testRep.pass("✅ Assertion Confirmatin Text: " + result);
		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows|New Tab Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows|New Tab Test Completed...");
	}

	@Test(priority = 4, description = "Test Alerts, Frames & Windows|Browser Windows|New Window...")
	public void browserWindowsNewWindow() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Window...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows|New Window");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Window...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String result = alertsframewindowsPage.clickNewWindow();

		Assert.assertEquals("This is a sample page", result);

		testRep.pass("✅ Assertion Confirmatin Text: " + result);
		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows|New Window Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows|New Window Test Completed...");
	}

	@Test(priority = 5, description = "Test Alerts, Frames & Windows|Browser Windows|New Window Message...")
	public void browserWindowsNewWindowMsg() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Browser Windows|New Window Message...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Browser Windows|New Window Message");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Window Message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickBrowserWindow();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		alertsframewindowsPage.clickNewWindowMessage();

		testRep.pass("✅ Test Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Browser Windows|New Window Message Test Completed...");
	}

	@Test(priority = 6, description = "Test Alerts, Frames & Windows|Alerts...")
	public void ClickAlerts() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Alerts");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|New Window Message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String alertsPgTitle = alertsframewindowsPage.clickAlerts();
		
		Assert.assertEquals("Alerts", alertsPgTitle);

		testRep.pass("✅ Assertion Confirmatin Text: " + alertsPgTitle);
		testRep.pass("✅ Test Alerts, Frames & Windows|Alerts Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Alerts Test Completed...");
	}

	@Test(priority = 7, description = "Test Alerts, Frames & Windows|Alerts|Click button to see Alert...")
	public void ClickToSeeAlerts() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Alerts|Click button to see Alert...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Alerts|Click button to see Alert");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Alerts|Click button to see Alert...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String result = alertsframewindowsPage.clickSeeAlert5sec();
		
		Assert.assertEquals("This alert appeared after 5 seconds", result);

		testRep.pass("✅ Assertion Confirmatin Text: " + result);
		testRep.pass("✅ Test Alerts, Frames & Windows|Alerts|Click button to see Alert Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Alerts|Click button to see Alert Test Completed...");
	}

	@Test(priority = 8, description = "Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept...")
	public void ClickAcceptConfirmationBox() {
		testRep = extentReportManager
				.createTest("Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept...");
		testRep.info(
				"🧪 Starting test for Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept");
		demoqaLog.info(
				"🧪 Starting Test Alerts, Frames & Windows|AlertsOn button click, confirm box will appear, Accept...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String confResult = alertsframewindowsPage.clickConfirmBoxAlertAccept();
		
		Assert.assertEquals("You selected Ok", confResult);

		testRep.pass("✅ Assertion Confirmatin Text: " + confResult);
		testRep.pass(
				"✅ Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept Test Completed...");
		demoqaLog.info(
				"✅ Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Accept Test Completed...");
	}

	@Test(priority = 9, description = "Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny...")
	public void ClickDenyConfirmationBox() {
		testRep = extentReportManager
				.createTest("Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny...");
		testRep.info(
				"🧪 Starting test for Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny");
		demoqaLog.info(
				"🧪 Starting Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String confResult = alertsframewindowsPage.clickConfirmBoxAlertDeny();
		
		Assert.assertEquals("You selected Cancel", confResult);

		testRep.pass("✅ Assertion Confirmatin Text: " + confResult);
		testRep.pass(
				"✅ Test Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny Test Completed...");
		demoqaLog.info(
				"✅ Alerts, Frames & Windows|Alerts|On button click, confirm box will appear, Deny Test Completed...");
	}

	@Test(priority = 10, description = "Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data...")
	public void ClickPromptBoxFill() {
		testRep = extentReportManager.createTest(
				"Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data...");
		testRep.info(
				"🧪 Starting test for Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data");
		demoqaLog.info(
				"🧪 Starting Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickAlerts();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String promptRslt = alertsframewindowsPage.clickPromptFill();
		
		Assert.assertEquals("You entered Java Selenium", promptRslt);

		testRep.pass("✅ Assertion Confirmatin Text: " + promptRslt);
		testRep.pass(
				"✅ Test Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data Test Completed...");
		demoqaLog.info(
				"✅ Alerts, Frames & Windows|Alerts|On button click, prompt box will appear, Fill data Test Completed...");
	}

	@Test(priority = 11, description = "Test Alerts, Frames & Windows|Frames...")
	public void ClickFrames() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		String framesPgTitle = alertsframewindowsPage.clickFrames();
		
		Assert.assertEquals("Frames", framesPgTitle);

		testRep.pass("✅ Assertion Confirmatin Text: " + framesPgTitle);
		testRep.pass("✅ Test Alerts, Frames & Windows|Frames Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Frames Test Completed...");
	}

	@Test(priority = 12, description = "Test Alerts, Frames & Windows|Frames|Frame1 message...")
	public void GetFrame1() {
	    testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Frame1 message...");
	    testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Frame1 message");
	    demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Frame1 message...");

	    alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
	    alertsframewindowsPage.accessAlertFramesWindows();
	    alertsframewindowsPage.clickFrames();
	    PageLoadHandler.waitUntilLoaded(driver, 30);

	    String frame1msg = alertsframewindowsPage.GetFrame1msg();

	    Assert.assertNotNull(frame1msg, "Frame1 message retrieval failed or frame not ready.");
	    Assert.assertEquals(frame1msg, "This is a sample page", "Frame1 message mismatch.");

	    testRep.pass("✅ Assertion Confirmatin Text: " + frame1msg);
	    testRep.pass("✅ Test Alerts, Frames & Windows|Frame1 message Test Completed...");
	    demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Frame1 message Test Completed...");
	}

	@Test(priority = 13, description = "Test Alerts, Frames & Windows|Frames|Frame2 message...")
	public void GetFrame2() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Frame2 message...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Frame2 message");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Frame2 message...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		alertsframewindowsPage.clickFrames();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String frame2msg = alertsframewindowsPage.GetFrame2msg();
		
		Assert.assertEquals("This is a sample page", frame2msg);

		testRep.pass("✅ Assertion Confirmatin Text: " + frame2msg);
		testRep.pass("✅ Test Alerts, Frames & Windows|Frame2 message Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Frame2 message Test Completed...");
	}

	@Test(priority = 14, description = "Test Alerts, Frames & Windows|Nested Frames...")
	public void ClickNestedFrames() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Nested Frames...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Nested Frames");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Nested Frames...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String nestedframesPgTitle = alertsframewindowsPage.clickNestedFrames();
		
		Assert.assertEquals("Nested Frames", nestedframesPgTitle);

		testRep.pass("✅ Assertion Confirmatin Text: " + nestedframesPgTitle);
		testRep.pass("✅ Test Alerts, Frames & Windows|Nested Frames Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Nested Frames Test Completed...");
	}

	@Test(priority = 15, description = "Test Alerts, Frames & Windows|Frames|Parent Frame message...")
	public void GetParentFrame() {
	    testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Parent Frame message...");
	    testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Parent Frame message");
	    demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Parent Frame message...");

	    alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
	    alertsframewindowsPage.accessAlertFramesWindows();
	    alertsframewindowsPage.clickNestedFrames();
	    PageLoadHandler.waitUntilLoaded(driver, 30);

	    String frameText = alertsframewindowsPage.GetParentFramemsg();

	    Assert.assertNotNull(frameText, "Parent frame message retrieval failed.");
	    Assert.assertTrue(frameText.contains("Parent frame"), "Unexpected frame content: " + frameText);

	    testRep.pass("✅ Assertion Confirmatin Text: " + frameText);
	    testRep.pass("✅ Test Alerts, Frames & Windows|Frames|Parent Frame message Test Completed...");
	    demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Parent Frame message Test Completed...");
	}
	
	@Test(priority = 16, description = "Test Alerts, Frames & Windows|Frames|Child Frame message...")
	public void GetChildFrame() {
	    testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Frames|Child Frame message...");
	    testRep.info("🧪 Starting test for Alerts, Frames & Windows|Frames|Child Frame message");
	    demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Frames|Child Frame message...");

	    alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
	    alertsframewindowsPage.accessAlertFramesWindows();
	    alertsframewindowsPage.clickNestedFrames();
	    PageLoadHandler.waitUntilLoaded(driver, 30);

	    String frameText = alertsframewindowsPage.GetChildFramemsg();

	    Assert.assertNotNull(frameText, "Child frame message retrieval failed.");
	    Assert.assertTrue(frameText.contains("Child Iframe"), "Unexpected frame content: " + frameText);

	    testRep.pass("✅ Assertion Confirmatin Text: " + frameText);
	    testRep.pass("✅ Test Alerts, Frames & Windows|Frames|Child Frame message Test Completed...");
	    demoqaLog.info("✅ Alerts, Frames & Windows|Frames|Child Frame message Test Completed...");
	}

	@Test(priority = 17, description = "Test Alerts, Frames & Windows|Modal Dialogs...")
	public void ClickModalDialogs() {
		testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Modal Dialogs...");
		testRep.info("🧪 Starting test for Alerts, Frames & Windows|Modal Dialogs");
		demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Modal Dialogs...");
		alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
		alertsframewindowsPage.accessAlertFramesWindows();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String modalDialogsPgTitle = alertsframewindowsPage.clickModalDialogs();
		
		Assert.assertEquals("Modal Dialogs", modalDialogsPgTitle);

		testRep.pass("✅ Assertion Confirmatin Text: " + modalDialogsPgTitle);
		testRep.pass("✅ Test Alerts, Frames & Windows|Modal Dialogs Test Completed...");
		demoqaLog.info("✅ Alerts, Frames & Windows|Modal Dialogs Test Completed...");
	}

	@Test(priority = 18, description = "Test Alerts, Frames & Windows|Modal Dialogs|Small Modal click...")
	public void ClickSmallModal() {
	    testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Modal Dialogs|Small Modal click...");
	    testRep.info("🧪 Starting test for Alerts, Frames & Windows|Modal Dialogs|Small Modal click");
	    demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Modal Dialogs|Small Modal...");

	    alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
	    alertsframewindowsPage.accessAlertFramesWindows();
	    alertsframewindowsPage.clickModalDialogs();
	    PageLoadHandler.waitUntilLoaded(driver, 30);

	    Map<String, String> modalContent = alertsframewindowsPage.clickSmallModal();

	    Assert.assertNotNull(modalContent, "Modal content retrieval failed.");
	    Assert.assertEquals(modalContent.get("title"), "Small Modal", "Modal title mismatch.");
	    Assert.assertTrue(modalContent.get("body").contains("This is a small modal"), "Unexpected modal body content.");

	    testRep.pass("✅ Assertion Confirmatin Text: " + modalContent);
	    testRep.pass("✅ Test Alerts, Frames & Windows|Modal Dialogs|Small Modal click Test Completed...");
	    demoqaLog.info("✅ Alerts, Frames & Windows|Modal Dialogs|Small Modal click Test Completed...");
	}

	@Test(priority = 19, description = "Test Alerts, Frames & Windows|Modal Dialogs|Large Modal click...")
	public void ClickLargeModal() {
	    testRep = extentReportManager.createTest("Test Alerts, Frames & Windows|Modal Dialogs|Large Modal click...");
	    testRep.info("🧪 Starting test for Alerts, Frames & Windows|Modal Dialogs|Large Modal click");
	    demoqaLog.info("🧪 Starting Test Alerts, Frames & Windows|Modal Dialogs|Large Modal...");

	    alertsframewindowsPage alertsframewindowsPage = new alertsframewindowsPage(driver);
	    alertsframewindowsPage.accessAlertFramesWindows();
	    alertsframewindowsPage.clickModalDialogs();
	    PageLoadHandler.waitUntilLoaded(driver, 30);

	    Map<String, String> modalContent = alertsframewindowsPage.clickLargeModal();

	    Assert.assertNotNull(modalContent, "Modal content retrieval failed.");
	    Assert.assertEquals(modalContent.get("title"), "Large Modal", "Modal title mismatch.");
	    Assert.assertTrue(modalContent.get("body").contains("Lorem Ipsum is simply dummy text"), "Unexpected modal body content.");

	    testRep.pass("✅ Test Alerts, Frames & Windows|Modal Dialogs|Large Modal click Test Completed...");
	    demoqaLog.info("✅ Alerts, Frames & Windows|Modal Dialogs|Large Modal click Test Completed...");
	}
}
