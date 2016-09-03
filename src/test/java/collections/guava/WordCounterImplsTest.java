package collections.guava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordCounterImplsTest {

    @Test
    public void testGuavaCounter() {
        test(new WordCounterGuavaImpl());
    }

    @Test
    public void testCoreJavaCounter() {
        test(new WordCounterCoreJavaImpl());
    }

    private void test(WordCounter counter) {
        String phrase =
                "Removes and returns all the values associated with the specified key. " +
                        "The returned collection may or may not be modifiable, " +
                        "but modifying it will not affect the multimap";
        String[] words = phrase.toLowerCase().split("[ ,]+");
        for (String word : words) {
            counter.addWord(word);
        }
        assertEquals(0, counter.getWordCount("set"));
        assertEquals(1, counter.getWordCount("multimap"));
        assertEquals(2, counter.getWordCount("not"));
        assertEquals(4, counter.getWordCount("the"));
    }
}
