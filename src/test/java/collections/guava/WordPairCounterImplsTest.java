package collections.guava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordPairCounterImplsTest {
    @Test
    public void testGuavaCounter() {
        test(new WordPairCounterGuavaImpl());
    }

    @Test
    public void testCoreJavaCounter() {
        test(new WordPairCounterCoreJavaImpl());
    }

    private void test(WordPairCounter counter) {
        String phrase =
                "Men in white coats\n" +
                "Bring the changes\n" +
                "Call us Fathers\n" +
                "Call us strangers\n" +
                "\n" +
                "Men in white coats\n" +
                "Making white heat\n" +
                "Kill or cure you\n" +
                "\n" +
                "We know what is best for you\n" +
                "Run just one more test for you\n" +
                "We know what we're doing don't we\n" +
                "We know what we're doing don't we";
        String[] words = phrase.toLowerCase().split("[ \n]+");
        for (int i = 0; i < words.length - 1; i++) {
            counter.addWordPair(words[i], words[i + 1]);
        }
        assertEquals(0, counter.getWordPairCount("song", "text"));
        assertEquals(1, counter.getWordPairCount("kill", "or"));
        assertEquals(2, counter.getWordPairCount("white", "coats"));
        assertEquals(3, counter.getWordPairCount("we", "know"));
    }
}
