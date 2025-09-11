package utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PauseManager {

    private static final Logger demoqaLog = LogManager.getLogger(PauseManager.class);


    public static void handlePause(int currentCount, ExtentTest systemEventTest) {
        int batchSize = ConfigLoader.getInt("pause.after.tests", 20);
        int pauseMinutes = ConfigLoader.getInt("pause.duration.minutes", 5);

        if (batchSize > 0 && (currentCount % batchSize == 0)) {
            int pauseSeconds = pauseMinutes * 60;
            String pauseMsg = "⏸ Pausing for " + pauseMinutes + " minutes after " + currentCount + " tests...";
            demoqaLog.warn(pauseMsg);
            if (systemEventTest != null) systemEventTest.info(pauseMsg);

            try {
                for (int i = pauseSeconds; i > 0; i--) {
                    if (i % 60 == 0) {
                        String msg = "▶ Resuming in " + (i / 60) + " minutes...";
                        demoqaLog.info(msg);
                        if (systemEventTest != null) systemEventTest.info(msg);
                    } else if (i <= 10) {
                        String msg = "▶ Resuming in " + i + " seconds...";
                        demoqaLog.info(msg);
                        if (systemEventTest != null) systemEventTest.info(msg);
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                demoqaLog.warn("Pause interrupted: {}", e.getMessage());
                if (systemEventTest != null) systemEventTest.warning("Pause interrupted: " + e.getMessage());
            }

            demoqaLog.info("✅ Pause complete. Resuming tests...");
            if (systemEventTest != null) systemEventTest.info("✅ Pause complete. Resuming tests...");
        }
    }
}