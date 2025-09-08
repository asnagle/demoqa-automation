package utils;

import java.io.File;

public class ChromeProfileCleaner {

    public static void cleanChromeProfiles() {
        String tempDir = System.getProperty("java.io.tmpdir"); // AppData\Local\Temp on Windows
        File dir = new File(tempDir);

        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) ->
                    name.startsWith("scoped_dir") ||
                    name.startsWith("chrome_profile") ||
                    name.startsWith("chrome-user-data") ||
                    name.startsWith("chrome-profile-")); // your custom profiles

            if (files != null) {
                for (File f : files) {
                    deleteRecursive(f);
                }
            }
        }
    }

    private static void deleteRecursive(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursive(child);
                }
            }
        }
        if (!file.delete()) {
            file.deleteOnExit();
        }
    }
}
