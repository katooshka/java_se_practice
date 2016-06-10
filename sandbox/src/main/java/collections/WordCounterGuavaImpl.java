package collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class WordCounterGuavaImpl implements WordCounter {
    private Multiset<String> words = HashMultiset.create();

    @Override
    public void addWord(String word) {
        words.add(word);
    }

    @Override
    public int getWordCount(String word) {
        return words.count(word);
    }
}
