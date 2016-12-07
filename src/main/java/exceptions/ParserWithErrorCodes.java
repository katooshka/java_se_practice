package exceptions;

/**
 * Author: katooshka
 * Date: 1/6/16.
 */
public class ParserWithErrorCodes {
    public static void main(String[] args) {
        FullDate result = parseDate("0999/45/06");
        if (result == null) {
            System.out.println("");

        } else {
            System.out.println(result.toString());
        }
    }

    public static FullDate parseDate(String date) {
        if (!hasSlash(date)) {
            return null;
        }
        int day = parseDatePart(date, 8, 10);
        int month = parseDatePart(date, 5, 7);
        int year = parseDatePart(date, 0, 4);
        if (!isCorrectFormat(day, month, year, -1)) {
            return null;
        }
        if (!isCorrectDate(day, month, year)) {
            return null;
        }
        return new FullDate(day, month, year);
    }

    private static int parseDatePart(String date, int start, int end) {
        return parseNumber(date.substring(start, end));
    }

    private static int parseNumber(String number) {
        int result = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (c < '0' || c > '9') {
                return -1;
            }
            int d = c - '0';
            result = result * 10 + d;
        }
        return result;
    }

    private static boolean isCorrectFormat(int day, int month, int year, int sign) {
        return day != sign & month != sign & year != sign;
    }

    private static boolean isCorrectDate(int day, int month, int year) {
        if (day < 1 | day > 31) {
            return false;
        }
        if (month < 1 | month > 12) {
            return false;
        }
        if (year < 1900 | year > 3000) {
            return false;
        }
        return true;
    }

    private static boolean hasSlash(String date) {
        return date.length() > 7 && date.charAt(4) == '/' && date.charAt(7) == '/';
    }
}
