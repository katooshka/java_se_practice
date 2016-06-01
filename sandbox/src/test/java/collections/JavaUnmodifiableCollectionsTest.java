package collections;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JavaUnmodifiableCollectionsTest {

    @Test
    public void testUnmodifiableCollectionsCreation() {
        List<String> wrappedList = Collections.unmodifiableList(Arrays.asList("1", "2", "3"));
        assertEquals(3, wrappedList.size());

        Set<String> testSet = new HashSet<>();
        testSet.add("1");
        testSet.add("2");
        testSet.add("3");
        Set<String> wrappedSet = Collections.unmodifiableSet(testSet);
        assertEquals(3, wrappedSet.size());

        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "a");
        testMap.put(2, "b");
        testMap.put(3, "c");
        Map<Integer, String> wrappedMap = Collections.unmodifiableMap(testMap);
        assertEquals(3, wrappedMap.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableWrapperAlterationFail() {
        List<String> underlyingList = new ArrayList<>();
        underlyingList.add("1");
        underlyingList.add("2");
        underlyingList.add("3");
        List<String> wrappedList  = Collections.unmodifiableList(underlyingList);
        wrappedList.add("element");
    }

    @Test
    public void testUnderlyingListAlteration() {
        List<String> underlyingList = Arrays.asList("1", "2", "3");
        List unmodifiableList = Collections.unmodifiableList(underlyingList);
        underlyingList.set(0, "element");
        assertTrue(unmodifiableList.contains("element"));
    }

    @Test
    public void testMutableObjectsListAlteration() {
        List<String> mutable1 = new ArrayList<>();
        mutable1.add("1");
        mutable1.add("2");
        List<String> mutable2 = new ArrayList<>();
        mutable2.add("3");
        mutable2.add("4");
        List<List<String>> unmodifiableList = Collections.unmodifiableList(Arrays.asList(mutable1, mutable2));
        mutable1.add("5");
        assertTrue(unmodifiableList.get(0).contains("5"));
    }
}
