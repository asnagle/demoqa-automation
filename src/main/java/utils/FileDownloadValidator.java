package utils;

import java.io.File;

public class FileDownloadValidator {
	public static boolean isFileDownloaded(String downloadDir, String expectedFileName) {
	    File dir = new File(downloadDir);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) return false;

	    for (File file : files) {
	        if (file.getName().equalsIgnoreCase(expectedFileName)) {
	            return true;
	        }
	    }
	    return false;
	}

}
