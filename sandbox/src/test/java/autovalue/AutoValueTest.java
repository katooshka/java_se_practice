package autovalue;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AutoValueTest {
    @Test
    public void createStarWithAllRequiredFields() {
        Star.builder()
                .name("a Ori")
                .historicalName("Betelgeuse")
                .absoluteMagnitude(-5)
                .spectralClass("M")
                .temperature(3600)
                .build();
        Star.builder()
                .name("a Ori")
                .absoluteMagnitude(-5)
                .spectralClass("M")
                .temperature(3600)
                .build();
    }
    @Test(expected = IllegalStateException.class)
    public void createStarWithoutRequiredFields() {
        Star.builder()
                .name("a Vir")
                .spectralClass("B")
                .temperature(22400)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createStarWithBrokenValidation() {
        Star.builder()
                .name("a Aql")
                .historicalName("Altair")
                .absoluteMagnitude(2)
                .spectralClass("L")
                .temperature(8000)
                .build();
    }
    @Test
    public void compareStars() {
        Star star1 = Star.builder()
                .name("b Per")
                .historicalName("Algol")
                .absoluteMagnitude(0)
                .spectralClass("B")
                .temperature(12000)
                .build();
        Star star2 = Star.builder()
                .name("b Per")
                .historicalName("Algol")
                .absoluteMagnitude(0)
                .spectralClass("B")
                .temperature(12000)
                .build();
        assertEquals(star1, star2);
    }
}
