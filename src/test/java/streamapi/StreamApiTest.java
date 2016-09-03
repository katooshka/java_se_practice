package streamapi;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.junit.Assert.*;

public class StreamApiTest {
    @Test
    public void testFilter() {
        List<String> list = asList("cat", "dog", "rabbit", "sparrow", "frog", "cow");
        assertEquals(list.stream().filter(s -> s.contains("a")).count(), 3);
    }

    @Test
    public void testSkip() {
        IntStream stream = IntStream.of(555, 11, 2, 67, 33, 0, 555);
        assertEquals(1, stream.skip(1).filter(v -> v == 555).count());
    }

    @Test
    public void testDistinct() throws IOException {
        String[] withDuplicates = ("Stream represents Stream represents a sequence of of " +
                "objects from from a source, source, a which supports aggregate aggregate aggregate operations. " +
                "represents represents sequence of of")
                .split(" ");
        String[] withoutDuplicates =
                "Stream represents a sequence of objects from source, which supports aggregate operations.".split(" ");
        assertEquals(
                asList(withoutDuplicates),
                Arrays.stream(withDuplicates).distinct().collect(Collectors.toList()));
    }

    @Test
    public void testMap() {
        IntStream stream = IntStream.of(0, 1, 2, 3);
        assertEquals(asList("number: 0", "number: 1", "number: 2", "number: 3"),
                stream.mapToObj(Integer::toString).map(s -> "number: " + s).collect(Collectors.toList()));
    }

    @Test
    public void testPeek() {
        Stream<String> initialList = Stream.of("Q", "W", "E", "R", "T", "Y");
        List<String> resultList = new ArrayList<>();
        List<String> changedList = initialList.peek(resultList::add).collect(Collectors.toList());
        assertEquals(asList("Q", "W", "E", "R", "T", "Y"), changedList);
        assertEquals(asList("Q", "W", "E", "R", "T", "Y"), resultList);
    }

    @Test
    public void testLimit() {
        Stream<String> list = Stream.of("1", "2", "3", "A", "B");
        assertEquals(asList("1", "2", "3"), list.limit(3).collect(Collectors.toList()));
    }

    @Test
    public void testSorted() {
        int[] array = new int[]{123, 54, 4, 87, 0, 5, 2, 234, 9, 68};
        int[] sorted = new int[]{0, 2, 4, 5, 9, 54, 68, 87, 123, 234};
        assertArrayEquals(sorted, Arrays.stream(array).sorted().toArray());
    }

    @Test
    public void testFlatMap() {
        Stream<String> list = Stream.of(
                "Stream represents a sequence of",
                "objects from a source,",
                "which supports aggregate operations.");
        List<String> result = list
                .flatMap(s -> stream(s.split(" ")))
                .collect(Collectors.toList());
        List<String> expected =
                asList("Stream represents a sequence of objects from a source, which supports aggregate operations."
                        .split(" "));
        assertEquals(expected, result);
    }

    @Test
    public void testFindFirst() {
        IntStream stream = IntStream.of(12, 23, 34, 45, 56);
        assertEquals(12, stream.findFirst().orElse(-1));
    }

    @Test
    public void testMatch() {
        assertTrue(asList("7", "2", "", "6", "0", "4").stream().anyMatch(String::isEmpty));
        assertTrue(asList("7", "2", "3", "6", "0", "4").stream().noneMatch(String::isEmpty));
        assertTrue(asList("", "", "", "", "", "").stream().allMatch(String::isEmpty));
    }

    @Test
    public void testMinMax() {
        assertEquals(0, IntStream.of(9, 6, 4, 0, 1, 3).min().orElse(-1));
        assertEquals(9, IntStream.of(9, 6, 4, 0, 1, 3).max().orElse(-1));
    }

    @Test
    public void testForEach() {
        IntStream initialList = IntStream.of(-1, 0, 1);
        List<Integer> resultList = new ArrayList<>();
        initialList.forEach(e -> resultList.add(e + 1));
        assertEquals(asList(0, 1, 2), resultList);
    }

    @Test
    public void testReduce() {
        OptionalInt result = IntStream.of(3000, -5000, 2000).reduce((s1, s2) -> s1 + s2);
        assertEquals(0, result.orElse(-1));
    }

    @Test
    public void testOddNumbersSum() {
        assertEquals(2550, IntStream.range(0, 101).filter(s -> s % 2 == 0).sum());
    }

    @Test
    public void testRandomElementsCreation() {
        int[] result = new Random().ints(100, -50, 51).toArray();
        assertEquals(100, result.length);
        assertTrue(Arrays.stream(result).allMatch(x -> x <= 50 && x >= -50));
    }

    @Test
    public void testToSetCollectors() {
        List<String> people = asList("Katya", "Zhenya", "Aydar", "Katya", "Zhenya");
        assertEquals(ImmutableSet.of(5, 6), people.stream().map(String::length).collect(Collectors.toSet()));
    }

    @Test
    public void testJoiningCollector() {
        assertEquals("1, 2, 3, 4, 5, 6, 7, 8", stream("1 2 3 4 5 6 7 8".split(" ")).collect(Collectors.joining(", ")));
    }

    @Test
    public void testSummingIntCollector() {
        int totalLength = Stream.of("Katya", "Zhenya", "Aydar").collect(Collectors.summingInt(String::length));
        assertEquals(16, totalLength);
    }

    @Test
    public void testGroupingByCollector() {
        List<Student> students = asList(
                new Student(100), new Student(60),
                new Student(60), new Student(82));

        Map<Integer, Integer> scoreDistribution = students
                .stream()
                .collect(Collectors.groupingBy(Student::getScore, Collectors.summingInt(s -> 1)));
        Map<Integer, Integer> expected = ImmutableMap.<Integer, Integer>builder()
                .put(100, 1)
                .put(60, 2)
                .put(82, 1)
                .build();
        assertEquals(expected, scoreDistribution);
    }


}
