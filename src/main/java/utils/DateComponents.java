package utils;

public class DateComponents {
	 private final int day;
	    private final int month;
	    private final int year;

	    public DateComponents(int day, int month, int year) {
	        this.day = day;
	        this.month = month;
	        this.year = year;
	    }

	    public int getDay() { return day; }
	    public int getMonth() { return month; }
	    public int getYear() { return year; }

	    @Override
	    public String toString() {
	        return String.format("%02d/%02d/%04d", day, month, year);
	    }


}
