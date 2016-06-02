package collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GuavaImmutableCollectionsTest {

    @Test
    public void createGuavaImmutableCollectionsWithCopyOf() {
        List<String> list = Arrays.asList("1", "2", "3");
        assertEquals(list, ImmutableList.copyOf(list));
    }

    @Test
    public void createGuavaImmutableCollectionsWithOf() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c");
        assertEquals(set, immutableSet);
    }

    @Test
    public void createGuavaImmutableCollectionsWithBuilder() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "s");
        map.put(3, "d");
        ImmutableMap<Integer, String> immutableMap = ImmutableMap.<Integer, String>builder()
                .put(1, "a")
                .put(2, "s")
                .put(3, "d")
                .build();
        assertEquals(map, immutableMap);

        List<String> list = Arrays.asList("1", "2", "3");
        ImmutableList<String> immutableList = ImmutableList.<String>builder()
                .add("1")
                .add("2")
                .add("3")
                .build();
        assertEquals(list, immutableList);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGuavaImmutableCollectionAlterationFail() {
        List<String> testList = ImmutableList.copyOf(new ArrayList<String>());
        testList.add("element");
    }

    @Test
    public void testUnderlyingListAlteration() {
        List<String> underlyingList = Arrays.asList("1", "2", "3");
        ImmutableList<String> immutableList = ImmutableList.copyOf(underlyingList);
        underlyingList.set(0, "element");
        assertFalse(immutableList.contains("element"));
    }

    @Test
    public void testMutableObjectsListAlteration() {
        List<String> mutable1 = new ArrayList<>();
        mutable1.add("1");
        mutable1.add("2");
        List<String> mutable2 = new ArrayList<>();
        mutable2.add("3");
        mutable2.add("4");
        ImmutableList<List<String>> immutableList = ImmutableList.of(mutable1, mutable2);
        immutableList.get(0).add("5");
        assertTrue(immutableList.get(0).contains("5"));
    }
}
