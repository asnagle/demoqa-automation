package pages;

import static org.testng.Assert.assertNotNull;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.demoqaBase;
import utils.ClickHandler;
import utils.WindowValidationUtils;
import utils.waitForElement;

public class alertsframewindowsPage extends demoqaBase {

	@FindBy(xpath = "//div[contains(@class,'col-12 mt-4 col-md-6')]")
	WebElement Cardpage;

	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'body-height')]/div[contains(@class,'container playgound-body')]/div[contains(@class,'row')]/div[contains(@class,'col-md-3')]/div[contains(@class,'left-pannel')]/div[contains(@class,'accordion')]/div[3]/span[1]/div[1]/div[1]")
	WebElement CardPgTitle;

	@FindBy(xpath = "//div[contains(@class,'element-list collapse show')]//li[@id='item-0']")
	WebElement BrowserWindows;

	@FindBy(xpath = "//h1[normalize-space()='Browser Windows']")
	WebElement BrowserWindowsPage;

	@FindBy(xpath = "//button[@id='tabButton']")
	WebElement NewTabBtn;

	@FindBy(xpath = "//h1[@id='sampleHeading']")
	WebElement NewTabresult;

	@FindBy(xpath = "//button[@id='windowButton']")
	WebElement NewWindowBtn;

	@FindBy(xpath = "//button[@id='messageWindowButton']")
	WebElement MessageWindowBtn;
	
	@FindBy(xpath = "/html/body/text()")
	WebElement MessageWindowBody;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-1']")
	WebElement Alerts;
	
	@FindBy(xpath = "//h1[normalize-space()='Alerts']")
	WebElement AlertsPageTitle;
	
	@FindBy(xpath = "//button[@id='alertButton']")
	WebElement seeAlertBtn;
	
	@FindBy(xpath = "//button[@id='timerAlertButton']")
	WebElement timerAlertButton;
	
	@FindBy(xpath = "//button[@id='confirmButton']")
	WebElement confirmboxButton;
	
	@FindBy(xpath = "//span[@id='confirmResult']")
	WebElement confirmResult;
	
	@FindBy(xpath = "//button[@id='promtButton']")
	WebElement promptBox;
	
	@FindBy(xpath = "//span[@id='promptResult']")
	WebElement promptResult;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-2']")
	WebElement Frames;
	
	@FindBy(xpath = "//h1[normalize-space()='Frames']")
	WebElement FramepgTitle;
	
	@FindBy(id = "frame1")
	WebElement frame1;
	
	@FindBy(id = "frame2")
	WebElement frame2;
	
	@FindBy(id = "sampleHeading")
	WebElement frame1Content;
	
	@FindBy(id = "sampleHeading")
	WebElement frame2Content;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-3']")
	WebElement nestedFrames;
	
	@FindBy(xpath = "//h1[normalize-space()='Nested Frames']")
	WebElement nestedFramePgTitle;
	
	@FindBy(xpath = "//iframe[@id='frame1']")
	WebElement parentFrame;
	
	@FindBy(xpath = "//iframe[@srcdoc='<p>Child Iframe</p>']")
	WebElement childFrame;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-4']")
	WebElement modalDialogs;
	
	@FindBy(xpath = "//h1[normalize-space()='Modal Dialogs']")
	WebElement ModalDiagPgTitle;
	
	@FindBy(xpath = "//button[@id='showSmallModal']")
	WebElement modalSmallBtn;
	
	@FindBy(xpath = "//div[@id='example-modal-sizes-title-sm']")
	WebElement smallModalTitle;
	
	@FindBy(xpath = "//div[@class='modal-body']")
	WebElement smallModalBody;
	
	@FindBy(xpath = "//button[@id='closeSmallModal']")
	WebElement smallModalCloseBtn;
	
	@FindBy(xpath = "//button[@id='showLargeModal']")
	WebElement modalLargeBtn;
	
	@FindBy(xpath = "//div[@id='example-modal-sizes-title-lg']")
	WebElement largeModalTitle;
	
	@FindBy(xpath = "//p[contains(text(),'Lorem Ipsum is simply dummy text of the printing a')]")
	WebElement largeModalBody;
	
	@FindBy(xpath = "//button[@id='closeLargeModal']")
	WebElement largeModalCloseBtn;
	
	
	private WebDriverWait wait;

	public alertsframewindowsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		PageFactory.initElements(driver, this);
	}

	public String accessAlertFramesWindows() {

		demoqaLog.info("Accessing Alerts, Frame & Windows Card...");
		homePage homePage = new homePage(driver);
		System.out.println("Declared Instance WebDriverWait: " + wait);
		homePage.clickAlertsFrameWindowsCard();
		System.out.println("Title of this Page is: " + driver.getTitle());
		System.out.println("You are Accessing: " + CardPgTitle.getText());
		String cardpage = Cardpage.getText();
		demoqaLog.info("Alerts, Frame & Windows Card: " + cardpage);
		
		return cardpage;

	}

	public String clickBrowserWindow() {
		demoqaLog.info("Clicking on Alerts, Frame & Windows Card|Browser Windows...");
		BrowserWindows.click();
		String browserWindowsPage = BrowserWindowsPage.getText();
		System.out.println("You are now Accessing: " + browserWindowsPage);
		demoqaLog.info("You are now Accessing: " + browserWindowsPage);

		return browserWindowsPage;
		
	}

	public String clickNewTab() {
		demoqaLog.info("Initiating New Tab click sequence...");

		String originalHandle = driver.getWindowHandle();
		assertNotNull("Original window handle is null", originalHandle);

		NewTabBtn.click();
		demoqaLog.info("Clicked New Tab button");

		String result = WindowValidationUtils.switchToNewTabAndValidateText(driver, originalHandle,
				By.xpath("//h1[@id='sampleHeading']"), "This is a sample page", demoqaLog);

		System.out.println("You are now accessing: " + result);
		return result;
	}

	public String clickNewWindow() {
	    demoqaLog.info("Initiating New Window click sequence...");

	    String originalHandle = driver.getWindowHandle();
	    assertNotNull("Original window handle is null", originalHandle);

	    // Wait and click using utility method
	    waitForElement.waitAndClick(driver, NewWindowBtn);
	    demoqaLog.info("Clicked New Window button");

	    // Validate new tab and extract text
	    
	    String result = WindowValidationUtils.switchToNewTabAndValidateText(
	        driver,
	        originalHandle,
	        By.xpath("//h1[@id='sampleHeading']"),
	        "This is a sample page",
	        demoqaLog
	    );

	    System.out.println("You are now accessing: " + result);
	    return result;
	}
	
	public void clickNewWindowMessage() {
	    demoqaLog.info("🔍 Starting 'New Window Message' click sequence...");

	    // Get original handle
	    String originalHandle = driver.getWindowHandle();
	    assertNotNull("Original window handle is null", originalHandle);

	    // Click on the button
	    ClickHandler.smartClick(driver, MessageWindowBtn);

	    // Validate new window opened and return to original
	    WindowValidationUtils.switchToNewWindowAndValidate(
	        driver,
	        originalHandle,
	        demoqaLog
	    );

	    demoqaLog.info("✅ 'New Window Message' flow completed successfully.");
	}
	
	public String clickAlerts() {
	    demoqaLog.info("🔍 Starting test for Alerts click ..");

	    ClickHandler.smartClick(driver, Alerts);
	    demoqaLog.info("✅ Clicked on Alerts...");
	    String alertsPgTitle = AlertsPageTitle.getText();
	    demoqaLog.info("Page Title is: " + alertsPgTitle);
	    
	    return alertsPgTitle;
	    
	}
	
	public void clickToSeeAlert() {
		demoqaLog.info("🔍 Starting test for Click Button to see Alert...");

	    ClickHandler.smartClick(driver, seeAlertBtn);
	    demoqaLog.info("✅ Clicked on Click Button to see Alert...");
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ Click Button to see Alert message is: " + alert.getText());
        
        Assert.assertEquals("You clicked a button", alert.getText());
        alert.accept();
	}
	
	public String clickSeeAlert5sec() {
		demoqaLog.info("🔍 Starting test for On button click, alert will appear after 5 seconds...");

	    ClickHandler.smartClick(driver, timerAlertButton);
	    demoqaLog.info("✅ Clicked on Click Button to see Alert after 5 seconds...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, alert will appear after 5 seconds, message is: " + alert.getText());
        
        String result = alert.getText();
        alert.accept();
        return result;
	}
	
	public String clickConfirmBoxAlertAccept() {
		demoqaLog.info("🔍 Starting test for On button click, confirm box will appear, Accept...");
		waitForElement.waitUntilInteractable(driver, confirmboxButton);
	
	    ClickHandler.smartClick(driver, confirmboxButton);
	    demoqaLog.info("✅ Clicked On button click, confirm box will appear, Accept...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, confirm box will appear, message is: " + alert.getText());
        alert.accept();
        String confResult = confirmResult.getText();
        demoqaLog.info("✅ Your Selection was: " + confResult);
        
        return confResult;
	}
	
	public String clickConfirmBoxAlertDeny() {
		demoqaLog.info("🔍 Starting test for On button click, confirm box will appear, Deny...");

	    ClickHandler.smartClick(driver, confirmboxButton);
	    demoqaLog.info("✅ Clicked On button click, confirm box will appear, Deny...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, confirm box will appear, message is: " + alert.getText());
        alert.dismiss();
        String confResult = confirmResult.getText();
        demoqaLog.info("✅ Your Selection was: " + confResult);
        
        return confResult;
	}
	
	public String clickPromptFill() {
		demoqaLog.info("🔍 Starting test for On button click, prompt box will appear, Fill data...");
	    ClickHandler.smartClick(driver, promptBox);
	    
	    demoqaLog.info("✅ Clicked On button click, prompt box will appear, Fill data...");
	    Alert alert = driver.switchTo().alert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, prompt box will appear, message is: " + alert.getText());
        Assert.assertEquals("Please enter your name", alert.getText());
        alert.sendKeys("Java Selenium");
        alert.accept();
        String promptRslt = promptResult.getText();
        demoqaLog.info("✅ Your Selection was: " + promptRslt);
        
        return promptRslt;
	}
	
	public String clickFrames() {
	    demoqaLog.info("🔍 Starting test for Frames click ..");
	    waitForElement.waitUntilInteractable(driver, Frames);
	    ClickHandler.smartClick(driver, Frames);
	    demoqaLog.info("✅ Clicked on Frames...");
	    String framesPgTitle = FramepgTitle.getText();
	    demoqaLog.info("Page Title is: " + framesPgTitle);
	    
	    return framesPgTitle;
	}
	
	public String GetFrame1msg() {
	    demoqaLog.info("🔍 Starting test for Frames|Frame1 Message...");

	    By frame1Locator = By.id("frame1");
	    boolean frameReady = waitForElement.waitForFrameAndSwitch(driver, frame1Locator, 10);

	    if (!frameReady) {
	        demoqaLog.error("❌ Frame1 not available or not loaded in time.");
	        return null;
	    }

	    demoqaLog.info("✅ Switched to Frame1...");

	    By contentLocator = By.id("sampleHeading");
	    WebElement frame1Content;
	    try {
	        frame1Content = new WebDriverWait(driver, Duration.ofSeconds(5))
	                .until(ExpectedConditions.visibilityOfElementLocated(contentLocator));
	    } catch (TimeoutException e) {
	        demoqaLog.error("❌ Frame1 content not visible in time.");
	        return null;
	    }

	    waitForElement.isElementVisible(driver, frame1Content);
	    String frame1msg = frame1Content.getText();
	    demoqaLog.info("Frame1 contains: " + frame1msg);

	    driver.switchTo().defaultContent();
	    return frame1msg;
	}
	
	public String GetFrame2msg() {
	    demoqaLog.info("🔍 Starting test for Frames|Frame2 Message...");

	    By frame2Locator = By.id("frame2");
	    boolean frameReady = waitForElement.waitForFrameAndSwitch(driver, frame2Locator, 10);

	    if (!frameReady) {
	        demoqaLog.error("❌ Frame2 not available or not loaded in time.");
	        return null;
	    }

	    demoqaLog.info("✅ Switched to Frame2...");

	    By contentLocator = By.id("sampleHeading");
	    WebElement frame2Content;
	    try {
	        frame2Content = new WebDriverWait(driver, Duration.ofSeconds(5))
	                .until(ExpectedConditions.visibilityOfElementLocated(contentLocator));
	    } catch (TimeoutException e) {
	        demoqaLog.error("❌ Frame2 content not visible in time.");
	        return null;
	    }

	    waitForElement.isElementVisible(driver, frame2Content);
	    String frame2msg = frame2Content.getText();
	    demoqaLog.info("Frame2 contains: " + frame2msg);

	    driver.switchTo().defaultContent();
	    return frame2msg;
	}
	
	public String clickNestedFrames() {
		demoqaLog.info("🔍 Starting test for Frames|Nested Frames click ..");

	    ClickHandler.smartClick(driver, nestedFrames);
	    demoqaLog.info("✅ Clicked on Nested Frames...");
	    
	    waitForElement.isElementVisible(driver, nestedFramePgTitle);
	    String nestedframesPgTitle = nestedFramePgTitle.getText();
	    demoqaLog.info("Page Title is: " + nestedframesPgTitle);
	    
	    return nestedframesPgTitle;
	}

	
	public String GetParentFramemsg() {
	    demoqaLog.info("🔍 Starting test for Frames|Parent Frame Message...");
	    waitForElement.isElementVisible(driver, parentFrame);

	    // Wait and switch to parent frame using utility method
	    boolean frameSwitched = waitForElement.waitForFrameAndSwitch(driver, By.id("frame1"), 30);
	    if (!frameSwitched) {
	        demoqaLog.error("❌ Parent frame not found or not available within timeout.");
	        return null;
	    }

	    String frameText;
	    try {
	        WebElement body = waitForElement.getVisibleElement(driver, By.tagName("body"));
	        frameText = body.getText().trim();
	        demoqaLog.info("After switching, frame body contains: [" + frameText + "]");
	    } catch (NoSuchElementException | TimeoutException e) {
	        demoqaLog.error("❌ Unable to locate body tag inside parent frame: " + e.getMessage());
	        return null;
	    } finally {
	        driver.switchTo().defaultContent();
	    }

	    return frameText;
	}
	
	public String GetChildFramemsg() {
	    demoqaLog.info("🔍 Starting test for Frames|Child Frame Message...");

	    try {
	        driver.switchTo().frame(parentFrame);
	    } catch (NoSuchFrameException e) {
	        demoqaLog.error("❌ Parent frame not found: " + e.getMessage());
	        return null;
	    }

	    if (!driver.getPageSource().contains("Parent frame")) {
	        demoqaLog.warn("Parent frame content not found.");
	    }

	    try {
	        driver.switchTo().frame(childFrame);
	    } catch (NoSuchFrameException e) {
	        demoqaLog.error("❌ Child frame not found: " + e.getMessage());
	        return null;
	    }

	    if (!driver.getPageSource().contains("Child Iframe")) {
	        demoqaLog.warn("Child frame content not found in page source.");
	    }

	    String frameText;
	    try {
	        frameText = driver.findElement(By.tagName("body")).getText().trim();
	        demoqaLog.info("After switching, frame body contains: [" + frameText + "]");
	    } catch (NoSuchElementException e) {
	        demoqaLog.error("❌ Unable to locate body tag inside child frame.");
	        return null;
	    } finally {
	        driver.switchTo().defaultContent();
	    }

	    return frameText;
	}
	
	public String clickModalDialogs() {
		demoqaLog.info("🔍 Starting test for Frames|Modal Dialogs click ..");

	    ClickHandler.smartClick(driver, modalDialogs);
	    demoqaLog.info("✅ Clicked on Modal Dialogs...");
	    
	    String modalDialogsPgTitle = ModalDiagPgTitle.getText();
	    demoqaLog.info("Page Title is: " + modalDialogsPgTitle);
	    
	    return modalDialogsPgTitle;
	}
	
	public Map<String, String> clickSmallModal() {
	    demoqaLog.info("🔍 Starting test for Frames|Modal Dialogs|Small Modal click...");

	    ClickHandler.smartClick(driver, modalSmallBtn);
	    demoqaLog.info("✅ Clicked on Modal Dialogs|Small Modal...");

	    String smallmodalPgTitle = smallModalTitle.getText();
	    demoqaLog.info("Page Title is: " + smallmodalPgTitle);

	    String smallmodalBody = smallModalBody.getText();
	    demoqaLog.info("Small Modal Body Contains: " + smallmodalBody);

	    ClickHandler.smartClick(driver, smallModalCloseBtn);

	    Map<String, String> modalContent = new HashMap<>();
	    modalContent.put("title", smallmodalPgTitle);
	    modalContent.put("body", smallmodalBody);
	    return modalContent;
	}
	
	public Map<String, String> clickLargeModal() {
	    demoqaLog.info("🔍 Starting test for Frames|Modal Dialogs|Large Modal click...");

	    ClickHandler.smartClick(driver, modalLargeBtn);
	    demoqaLog.info("✅ Clicked on Modal Dialogs|Large Modal...");

	    waitForElement.isElementVisible(driver, largeModalTitle);

	    String largemodalPgTitle = largeModalTitle.getText();
	    demoqaLog.info("Page Title is: " + largemodalPgTitle);

	    String largemodalBody = largeModalBody.getText();
	    demoqaLog.info("Large Modal Body Contains: " + largemodalBody);

	    ClickHandler.smartClick(driver, largeModalCloseBtn);

	    Map<String, String> modalContent = new HashMap<>();
	    modalContent.put("title", largemodalPgTitle);
	    modalContent.put("body", largemodalBody);
	    return modalContent;
	}
	
}
