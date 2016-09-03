package generics;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;
import static org.junit.Assert.fail;

public class GenericsTest {
    @Test
    public void testTypeErasureViaReflection() {
        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        assertThat(strings.getClass()).isSameAs(integers.getClass());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testTypeErasureViaHeapPollution() {
        List<String> strings = new ArrayList<>(asList("a", "b", "c"));
        List<Integer> integers = (List<Integer>) (List<?>) strings;
        integers.set(0, 123);
        try {
            String myString = strings.get(0);
            fail("Expected ClassCastException");
        } catch (ClassCastException e) {
            // expected
        }
    }

    @Test
    public void testVarargsBehaviorWithObjectArrays() {
        String[] strings = new String[5];
        assertThat(asList(strings)).hasSize(5);
    }

    @Test
    public void testVarargsBehaviorWithPrimitiveArrays() {
        int[] ints = new int[5];
        assertThat(asList(ints)).hasSize(1);
    }

    @Test
    public void testClassToInstanceMap() {
        ClassToInstanceMap map = new ClassToInstanceMap();
        Pikachu pikachu = new Pikachu();
        map.put(Pokemon.class, pikachu);
        assertThat(map.get(Pikachu.class)).isNull();
        assertThat(map.get(Pokemon.class)).isSameAs(pikachu);
    }

    @Test
    public void testTypeLiteralToInstanceMap() {
        TypeLiteralToInstanceMap map = new TypeLiteralToInstanceMap();
        Pikachu pikachu = new Pikachu();
        map.put(new TypeLiteral<List<Pokemon>>(){}, ImmutableList.of(pikachu));
        assertThat(map.get(new TypeLiteral<List<Pokemon>>(){})).contains(pikachu);
        assertThat(map.get(new TypeLiteral<List<Pikachu>>(){})).isNull();
    }
}
