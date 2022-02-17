package per.eicho.demo.leetcode.q101_200;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>127. Word Ladder 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-ladder/">127. Word Ladder</a>
 */
public final class Q127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1. 1 <= beginWord.length <= 10
        // 2. endWord.length == beginWord.length
        // 3. 1 <= wordList.length <= 5000
        // 4. wordList[i].length == beginWord.length
        // 5. beginWord, endWord, and wordList[i] consist of lowercase English letters.
        // 6. beginWord != endWord
        // 7. All the words in wordList are unique.        
        
        final Map<String, Integer> map = new HashMap<>();
        final Integer defaultValue = Integer.MAX_VALUE;
        for (String word : wordList) {
            map.put(word, defaultValue);
        }
        map.put(beginWord, 1);

        if (!map.containsKey(endWord)) return 0;

        final Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        while (!queue.isEmpty()) {
            final String currentWord = queue.poll();
            final char[] wordChars = currentWord.toCharArray();
            final int currentLayer = map.get(currentWord);
            final Integer nextLayer = currentLayer + 1;

            for (int i = 0; i < wordChars.length; i++) {
                final char originalChar = wordChars[i];

                for (char j = 'a'; j <= 'z'; j++) {
                    wordChars[i] = j;

                    final String newWord = String.valueOf(wordChars);

                    if (!map.containsKey(newWord)) continue;
                    if (!map.get(newWord).equals(defaultValue)) continue;

                    queue.add(newWord);
                    map.put(newWord, nextLayer);
                }

                wordChars[i] = originalChar;
            }

            if (!map.get(endWord).equals(defaultValue)) return map.get(endWord);
        }

        return 0;
    }
}
