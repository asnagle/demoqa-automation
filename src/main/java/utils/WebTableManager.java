package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebTableManager {
	WebDriver driver;

    public WebTableManager(WebDriver driver) {
        this.driver = driver;
    }

    public int getUserCount() {
        return driver.findElements(By.cssSelector(".rt-tr-group")).size();
    }


}
