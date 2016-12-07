package exceptions;

import org.junit.Test;

import static exceptions.ParserWithExceptions.*;
import static org.junit.Assert.*;

/**
 * Author: katooshka
 * Date: 1/5/16.
 */
public class ParserWithExceptionTest {

    @Test
    public void parseDateTest() throws DateParseException, WrongDateException {
        assertEquals(new FullDate(3, 4, 2015), parseDate("2015/04/03"));
        assertEquals(new FullDate(26, 4, 1994), parseDate("1994/04/26"));
        assertEquals(new FullDate(1, 12, 1998), parseDate("1998/12/01"));
    }

    @Test(expected = DateParseException.class)
    public void invalidDateFormatTest() throws DateParseException, WrongDateException {
        parseDate("199+/03/45");
        parseDate("1990/-3/45");
        parseDate("1994/a0/45");
        parseDate("2005/11/:5");

    }

    @Test(expected = WrongDateException.class)
    public void invalidDateTest() throws DateParseException, WrongDateException {
        parseDate("3003/03/20");
        parseDate("2000/15/20");
        parseDate("2000/03/40");
    }
}
