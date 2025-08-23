package utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRenamer {
    public static void main(String[] args) {
        File folder = new File("C:/Dil Dosti Duniyadaari"); // 🔁 Update this path
        if (!folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        // Match: prefix + 1–2 digit number (not part of longer digit sequence) + suffix
        Pattern pattern = Pattern.compile("^(.*?)(?<!\\d)(\\d{1,2})(?!\\d)(.*)$");

        for (File file : folder.listFiles()) {
            if (!file.isFile()) continue;

            String fileName = file.getName();
            String baseName = fileName;
            String extension = "";

            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex != -1) {
                baseName = fileName.substring(0, dotIndex);
                extension = fileName.substring(dotIndex); // includes the dot
            }

            Matcher matcher = pattern.matcher(baseName);
            if (matcher.matches()) {
                String prefix = matcher.group(1);
                String numberStr = matcher.group(2);
                String suffix = matcher.group(3);

                int number = Integer.parseInt(numberStr);

                // ✅ Only pad if number is < 100 and numberStr has length < 3
                if (number < 100 && numberStr.length() < 3) {
                    String padded = String.format("%03d", number);
                    String newName = prefix + padded + suffix + extension;
                    File newFile = new File(folder, newName);

                    if (!newFile.exists()) {
                        boolean renamed = file.renameTo(newFile);
                        System.out.println((renamed ? "Renamed: " : "Failed: ") + fileName + " → " + newName);
                    } else {
                        System.out.println("Skipped (target exists): " + newName);
                    }
                } else {
                    System.out.println("Skipped (already 3 digits): " + fileName);
                }
            } else {
                System.out.println("Skipped (no match): " + fileName);
            }
        }
    }
}