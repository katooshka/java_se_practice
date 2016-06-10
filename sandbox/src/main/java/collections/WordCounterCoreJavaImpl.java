package collections;

import java.util.HashMap;
import java.util.Map;

public class WordCounterCoreJavaImpl implements WordCounter {
    private Map<String, Integer> words = new HashMap<>();

    @Override
    public void addWord(String word) {
        if (words.containsKey(word)) {
            words.put(word, words.get(word) + 1);
        } else {
            words.put(word, 1);
        }
    }

    @Override
    public int getWordCount(String word) {
        return words.containsKey(word) ? words.get(word) : 0;
    }
}
