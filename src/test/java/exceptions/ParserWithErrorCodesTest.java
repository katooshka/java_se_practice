package exceptions;

import org.junit.Test;


import static exceptions.ParserWithErrorCodes.*;
import static org.junit.Assert.assertEquals;

/**
 * Author: katooshka
 * Date: 1/7/16.
 */
public class ParserWithErrorCodesTest {
    @Test
    public void parseDateTest() {
        assertEquals(new FullDate(3, 4, 2015), parseDate("2015/04/03"));
        assertEquals(new FullDate(26, 4, 1994), parseDate("1994/04/26"));
        assertEquals(new FullDate(1, 12, 1998), parseDate("1998/12/01"));
    }

    @Test
    public void invalidDateOrDateFormatTest() {
        assertEquals(null, parseDate("199+/03/45"));
        assertEquals(null, parseDate("1990/-3/45"));
        assertEquals(null, parseDate("1994/a0/45"));
        assertEquals(null, parseDate("2005/11/:5"));
        assertEquals(null, parseDate("3003/03/20"));
        assertEquals(null, parseDate("2000/15/20"));
        assertEquals(null, parseDate("2000/03/40"));
    }
}
