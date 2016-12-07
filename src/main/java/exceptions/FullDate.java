package exceptions;

/**
 * Author: katooshka
 * Date: 1/4/16.
 */
public class FullDate {
    private int day;
    private int month;
    private int year;

    public FullDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        String transformedDay = Integer.toString(day);
        String transformedMonth = Integer.toString(month);
        String transformedYear = Integer.toString(year);
        if (day / 10 == 0) {
            transformedDay = '0' + transformedDay;
        }
        if (month / 10 == 0) {
            transformedMonth = '0' + transformedMonth;
        }
        return transformedYear + '/' + transformedMonth + '/' + transformedDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullDate data = (FullDate) o;

        return day == data.day && month == data.month && year == data.year;

    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }
}
