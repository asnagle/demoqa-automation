package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.demoqaLog;

public class homePage {
	private WebDriver driver;
	private By elementsCard = By.xpath("//div[@class='category-cards']//div[1]//div[1]//div[2]//*[name()='svg']");
	private By FormCard = By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]");
	

	public homePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickElementCard() {
		demoqaLog.info("Clicking on Elements Card...");
		driver.findElement(elementsCard).click();		
	}


	public void clickFormCard() {
		demoqaLog.info("Clicking on Form Card...");
		driver.findElement(FormCard).click();
		demoqaLog.info("Clicked on Form Card...");
	}



}
