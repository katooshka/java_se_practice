package exceptions;

/**
 * Author: katooshka
 * Date: 1/4/16.
 */
public class ParserWithExceptions {

    public static void main(String[] args) throws DateParseException, WrongDateException {
        System.out.println(parseDate("0999/45/06").toString());
    }

    public static FullDate parseDate(String date) throws DateParseException, WrongDateException {
        if (!hasSlash(date)){
            throw new DateParseException(date);
        }
        int day = parseDatePart(date, 8, 10);
        int month = parseDatePart(date, 5, 7);
        int year = parseDatePart(date, 0, 4);
        if (!isCorrectDate(day, month, year)){
            throw new WrongDateException(date);
        }
        return new FullDate(day, month, year);
    }

    private static int parseDatePart(String date, int start, int end) throws DateParseException {
        try {
            return parseNumber(date.substring(start, end));
        } catch (NumberParseException | StringIndexOutOfBoundsException e) {
            throw new DateParseException(date);
        }
    }

    private static int parseNumber(String number) throws NumberParseException {
        int result = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (c < '0' || c > '9') {
                throw new NumberParseException(number);
            }
            int d = c - '0';
            result = result * 10 + d;
        }
        return result;
    }

    private static boolean isCorrectDate(int day, int month, int year) throws WrongDateException {
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

class DateParseException extends Exception {
    public DateParseException(String date) {
        super(date);
    }
}

class NumberParseException extends Exception {
    public NumberParseException(String date) {
        super(date);
    }
}

class WrongDateException extends Exception {
    public WrongDateException(String date) {
        super(date);
    }
}
