package utils;

public class DateParts {
    public final int day;
    public final int month;
    public final int year;

    public DateParts(int day, int month, int year) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (year < 1900) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toIsoString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    public String toDisplayString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }

    @Override
    public String toString() {
        return toDisplayString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DateParts)) return false;
        DateParts other = (DateParts) obj;
        return day == other.day && month == other.month && year == other.year;
    }

    @Override
    public int hashCode() {
        return day * 10000 + month * 100 + year;
    }
}