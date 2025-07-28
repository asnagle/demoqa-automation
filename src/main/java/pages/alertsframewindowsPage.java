package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.demoqaBase;
import utils.demoqaLog;

public class alertsframewindowsPage extends demoqaBase {
	
	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'body-height')]/div[contains(@class,'container playgound-body')]/div[contains(@class,'row')]/div[contains(@class,'col-md-3')]/div[contains(@class,'left-pannel')]/div[contains(@class,'accordion')]/div[3]/span[1]/div[1]/div[1]")
	WebElement pageText;
	
	private WebDriverWait wait;

	public alertsframewindowsPage(WebDriver driver) {
		this.driver = driver;
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		PageFactory.initElements(driver, this);
	}
	
	public void accessAlertFramesWindows() {

		demoqaLog.info("Accessing Alerts, Frame & Windows Card...");
		homePage homePage = new homePage(driver);
		homePage.clickAlertsFrameWindowsCard();
		System.out.println("Title of this Page is: " + driver.getTitle());
		System.out.println("You can now access: " + pageText.getText());
		demoqaLog.info("Page: Alerts, Frame & Windows Card...");
	}
}
