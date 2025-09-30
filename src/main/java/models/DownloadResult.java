package models;

public class DownloadResult {
	private final boolean isDownloaded;
	private final String filePath;

	public DownloadResult(boolean isDownloaded, String filePath) {
		this.isDownloaded = isDownloaded;
		this.filePath = filePath;
	}

	public boolean isDownloaded() {
		return isDownloaded;
	}

	public String getFilePath() {
		return filePath;
	}
}