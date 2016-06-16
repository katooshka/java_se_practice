package collections.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class WordPairCounterGuavaImpl implements WordPairCounter{
    private Table<String, String, Integer> wordPairs = HashBasedTable.create();

    @Override
    public void addWordPair(String word1, String word2) {
        if (wordPairs.contains(word1, word2)) {
            wordPairs.put(word1, word2, wordPairs.get(word1, word2) + 1);
        } else {
            wordPairs.put(word1, word2, 1);
        }
    }

    @Override
    public int getWordPairCount(String word1, String word2) {
        return wordPairs.contains(word1, word2) ? wordPairs.get(word1, word2) : 0;
    }
}
