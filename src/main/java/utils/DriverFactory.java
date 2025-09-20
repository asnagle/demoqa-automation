package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

public class DriverFactory {

    private static final Logger demoqaLog = LogManager.getLogger(DriverFactory.class);
    private static Path tempProfileDir;

    public static WebDriver createDriver() {
        demoqaLog.info("🌐 Starting Chrome Web Browser...");

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER); // DOM ready, not full load
//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-application-cache");
        options.addArguments("--disk-cache-size=0");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-background-networking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments(
            "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"
        );
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        try {
            tempProfileDir = Files.createTempDirectory("chrome-profile-");
            options.addArguments("user-data-dir=" + tempProfileDir.toAbsolutePath());
            demoqaLog.info("✅ Created temporary Chrome profile at " + tempProfileDir.toAbsolutePath());
        } catch (IOException e) {
            demoqaLog.error("❌ Failed to create temp Chrome profile directory", e);
        }

        // Browser preferences
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");

        demoqaLog.info("🚀 WebDriver started successfully");
        return driver;
    }

    public static Path getTempProfileDir() {
        return tempProfileDir;
    }
}
