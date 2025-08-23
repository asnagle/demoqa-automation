package utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UndoFileRename {
    public static void main(String[] args) {
        File folder = new File("C:/Dil Dosti Duniyadaari"); // 🔁 Update this path
        if (!folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        // Match: prefix + 3-digit number with leading zeros + suffix
        Pattern pattern = Pattern.compile("^(.*?)(0{1,2}[1-9]\\d?)(.*)$");

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

                // Strip leading zeros
                String unpadded = String.valueOf(Integer.parseInt(numberStr));
                String newName = prefix + unpadded + suffix + extension;
                File newFile = new File(folder, newName);

                if (!newFile.exists()) {
                    boolean renamed = file.renameTo(newFile);
                    System.out.println((renamed ? "Restored: " : "Failed: ") + fileName + " → " + newName);
                } else {
                    System.out.println("Skipped (target exists): " + newName);
                }
            } else {
                System.out.println("Skipped (no match): " + fileName);
            }
        }
    }
}