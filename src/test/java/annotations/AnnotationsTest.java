package annotations;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnnotationsTest {

    @Test
    public void testGuardedByAnnotation() throws NoSuchFieldException {
        //noinspection ReflectionForUnavailableAnnotation
        assertFalse(Bank.class.getDeclaredField("moneyAmount").isAnnotationPresent(GuardedBy.class));
    }

    @Test
    public void testLogTimingsAnnotation() throws NoSuchMethodException {
        assertTrue(Bank.class.getDeclaredMethod("getMoneyAmount").isAnnotationPresent(LogTimings.class));
    }
}
