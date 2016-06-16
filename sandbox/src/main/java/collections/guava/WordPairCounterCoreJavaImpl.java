package collections.guava;

import java.util.HashMap;
import java.util.Map;

public class WordPairCounterCoreJavaImpl implements WordPairCounter {
    private Map<String, Map<String, Integer>> wordPairs = new HashMap<>();

    @Override
    public void addWordPair(String word1, String word2) {
        if (!wordPairs.containsKey(word1)) {
            wordPairs.put(word1, new HashMap<String, Integer>());
        }

        Map<String, Integer> firstWordMap = wordPairs.get(word1);
        if (firstWordMap.containsKey(word2)) {
            firstWordMap.put(word2, firstWordMap.get(word2) + 1);
        } else {
            firstWordMap.put(word2, 1);
        }
    }

    @Override
    public int getWordPairCount(String word1, String word2) {
        return wordPairs.containsKey(word1) && wordPairs.get(word1).containsKey(word2)
                ? wordPairs.get(word1).get(word2)
                : 0;
    }
}
