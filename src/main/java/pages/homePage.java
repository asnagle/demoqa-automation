package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ClickHandler;
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
        ClickHandler.smartClick(driver, ElementsCard);
        demoqaLog.info("Clicked on Elements Card...");
    }

    public void clickFormCard() {
        demoqaLog.info("Clicking on Form Card...");
        ClickHandler.smartClick(driver, FormCard);
        demoqaLog.info("Clicked on Forms Card...");
    }

    public void clickAlertsFrameWindowsCard() {
        demoqaLog.info("Clicking on Alert Frame & Windows Card...");
        ClickHandler.smartClick(driver, AlertsFrameWindowsCard);
        demoqaLog.info("Clicked on Alert Frame & Windows Card...");
    }

    public void clickWidgetsCard() {
        demoqaLog.info("Clicking on Widgets Card...");
        ClickHandler.smartClick(driver, WidgetsCard);
        demoqaLog.info("Clicked on Widgets Card...");
    }
}