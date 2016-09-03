package regex;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexTest {
    @Test
    public void testIpAddressRegex() {
        String regex =
                "^(((([1]\\d\\d)|([2][0-4]\\d)|([2]5[0-5]))|(\\d\\d)|(\\d))(\\.)){3}" +
                "((([1]\\d\\d)|([2][0-4]\\d)|([2]5[0-5]))|(\\d\\d)|(\\d))$";
        assertTrue("2.2.2.2".matches(regex));
        assertFalse("355.355.355.355".matches(regex));
        assertTrue("34.67.54.67".matches(regex));
        assertFalse("+.dcf.44.44".matches(regex));
        assertTrue("123.2.24.250".matches(regex));
        assertFalse("123.2.24.7.".matches(regex));
        assertFalse("255.2.24.300".matches(regex));
        assertFalse("256.2.24.300".matches(regex));
        assertFalse(" .2. .300".matches(regex));
        assertFalse("199.2.89.300".matches(regex));
        assertFalse("199....2.89.300".matches(regex));
        assertFalse("0000".matches(regex));
    }
}
