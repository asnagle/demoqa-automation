package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader()
                                                   .getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                System.err.println("⚠️ config.properties not found on classpath — using defaults.");
            }
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load config.properties", e);
        }
    }

    public static int getInt(String key, int defaultValue) {
        // 1) Check system property override
        String sys = System.getProperty(key);
        if (sys != null) {
            try {
                return Integer.parseInt(sys);
            } catch (NumberFormatException ex) {
                System.err.println("⚠️ Invalid system property for " + key + ": " + sys + " — using fallback.");
            }
        }
        // 2) Fall back to properties file or default
        try {
            return Integer.parseInt(props.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    public static boolean getBoolean(String key, boolean defaultValue) {
        String val = props.getProperty(key);
        if (val == null) return defaultValue;
        return Boolean.parseBoolean(val.trim());
    }
}
