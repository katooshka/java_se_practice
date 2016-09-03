package collections.guava;

public interface WordPairCounter {
    void addWordPair(String word1, String word2);

    int getWordPairCount(String word1, String word2);
}
