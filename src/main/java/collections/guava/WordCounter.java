package collections.guava;

public interface WordCounter {
    void addWord(String word);

    int getWordCount(String word);
}