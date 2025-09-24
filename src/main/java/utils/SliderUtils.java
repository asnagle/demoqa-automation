package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SliderUtils {

    private final WebDriver driver;
    private final Actions actions;

    // Step size for Actions-based dragging (optional)
    private static final int STEP_SIZE = 5;

    public SliderUtils(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    /**
     * JS-based slider movement (fast, reliable)
     */
    public void moveSliderToValueJS(By sliderLocator, int targetValue) {
        WebElement slider = driver.findElement(sliderLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
            slider, targetValue
        );
        System.out.printf("🎯 Slider moved to value (JS): %d%n", targetValue);
    }

    /**
     * Actions-based slider movement (simulates real drag)
     * Use only if necessary; may be flaky on small sliders.
     */
    public void moveSliderToValueActions(By sliderLocator, int targetValue) {
        WebElement slider = driver.findElement(sliderLocator);
        int currentValue = Integer.parseInt(slider.getAttribute("value"));
        int offsetX = (targetValue - currentValue) * STEP_SIZE;

        actions.moveToElement(slider)
               .clickAndHold()
               .moveByOffset(offsetX, 0)
               .release()
               .perform();

        System.out.printf("🎯 Slider moved to value (Actions): %d%n", targetValue);
    }

    /**
     * Wrapper to preserve old method signature
     */
    public void moveSliderToValue(By sliderLocator, int targetValue) {
        moveSliderToValueJS(sliderLocator, targetValue);
    }

    /**
     * Reads the current value of the slider element
     */
    public int getSliderValue(By sliderLocator) {
        WebElement slider = driver.findElement(sliderLocator);
        return Integer.parseInt(slider.getAttribute("value"));
    }

    /**
     * Reads displayed slider value from any separate display element
     */
    public int getDisplayedSliderValue() {
        WebElement display = driver.findElement(By.id("sliderValue"));
        return Integer.parseInt(display.getAttribute("value"));
    }
}
