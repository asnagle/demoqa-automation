package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProgressBarUtils {

    private final WebDriver driver;

    private static final By START_STOP_BUTTON = By.id("startStopButton");
    private static final By PROGRESS_BAR = By.cssSelector("div.progress-bar");

    public ProgressBarUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Starts or stops the progress bar
     */
    public void toggleProgressBar() {
        WebElement startStop = driver.findElement(START_STOP_BUTTON);
        JSclick.scrollAndClick(driver, startStop);
    }

    /**
     * Gets current progress value from aria-valuenow (more reliable than text)
     */
    public int getProgressValue() {
        WebElement progressBar = driver.findElement(PROGRESS_BAR);
        String value = progressBar.getAttribute("aria-valuenow"); // e.g., "57"
        return Integer.parseInt(value.trim());
    }

    /**
     * Waits until progress bar reaches target (slightly before), then stops it
     */
    public void waitUntilProgressReaches(int targetValue, int timeoutSeconds) {
        long endTime = System.currentTimeMillis() + timeoutSeconds * 1000;

        while (System.currentTimeMillis() < endTime) {
            int currentValue = getProgressValue();

            // ✅ Stop as soon as we hit target - 1 or more
            if (currentValue >= targetValue - 1) {
                System.out.printf("✅ Progress near target: %d%%%n", currentValue);
                toggleProgressBar(); // stop
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
                return;
            }

            try { Thread.sleep(70); } catch (InterruptedException ignored) {}
        }

        throw new RuntimeException("⏳ Timeout: Progress bar did not reach " + targetValue + "%");
    }



    /**
     * Moves progress bar to target value and stops it
     */
    public int moveProgressBarTo(int targetValue, int timeoutSeconds) {
        toggleProgressBar(); // start
        waitUntilProgressReaches(targetValue, timeoutSeconds);
        return getProgressValue(); // final stopped value
    }
}
