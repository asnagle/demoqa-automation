package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.demoqaLog;

public class homePage {
	private WebDriver driver;
	private By FormCard = By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]");
	private By PracticeForm = By.cssSelector("div.element-list.collapse.show > ul.menu-list > #item-0 > span.text");

	public homePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickFormCard() {
		demoqaLog.info("Clicking on FormCard...");
		driver.findElement(FormCard).click();
		demoqaLog.info("Clicked on FormCard...");
	}

	public void clickPracticeForm() {
		demoqaLog.info("Clicking on Practice Form...");
		driver.findElement(PracticeForm).click();
		demoqaLog.info("Clicked on Practice Form...");

	}

}
