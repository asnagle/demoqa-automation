package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.JSclick;
import utils.demoqaLog;

public class homePage {
	
	private WebDriver driver;
	private By ElementsCard = By.xpath("//div[@class='category-cards']//div[1]//div[1]//div[2]//*[name()='svg']");
	private By FormCard = By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]");
	private By AlertsFrameWindowsCard = By.xpath("//body/div[@id='app']/div[@class='body-height']/div[@class='home-content']/div[@class='home-body']/div[@class='category-cards']/div[3]/div[1]");
	private By WidgetsCard = By.xpath("//body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]");

	public homePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickElementCard() {
		demoqaLog.info("Clicking on Elements Card...");
//		driver.findElement(elementsCard).click();
		WebElement elementsCard = driver.findElement(ElementsCard);
		JSclick.scrollAndClick(driver, elementsCard);
		demoqaLog.info("Clicked on Elements Card...");
		
	}


	public void clickFormCard() {
		demoqaLog.info("Clicking on Form Card...");
//		driver.findElement(FormCard).click();
		WebElement FormsCard = driver.findElement(FormCard);
		JSclick.scrollAndClick(driver, FormsCard);
		demoqaLog.info("Clicked on Forms Card...");
	}
	
	public void clickAlertsFrameWindowsCard() {
		demoqaLog.info("Clicking on Alert Frame & Windows Card...");
//		driver.findElement(AlertsFrameWindowsCard).click();
		WebElement AlertsCard = driver.findElement(AlertsFrameWindowsCard);
		JSclick.scrollAndClick(driver, AlertsCard);
		demoqaLog.info("Clicked on on Alert Frame & Windows Card...");
	}
	
	public void clickWidgetsCard() {
		demoqaLog.info("Clicking on Widgets Card...");
		WebElement widgetsCard = driver.findElement(WidgetsCard);
		JSclick.scrollAndClick(driver, widgetsCard);
		demoqaLog.info("Clicked on on Widgets Card...");
	}


}
