package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class homePage {
	
	private WebDriver driver;
	private By FormCard = By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[2]");
//	private By PracticeForm = By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Forms'])[1]/following::span[1]");
	private By PracticeForm = By.cssSelector("div.element-list.collapse.show > ul.menu-list > #item-0 > span.text");
	
	
	public homePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickFormCard() {
		
		driver.findElement(FormCard).click();
	}
	
	public void clickPracticeForm() {
		driver.findElement(PracticeForm).click();
		
	}

}
