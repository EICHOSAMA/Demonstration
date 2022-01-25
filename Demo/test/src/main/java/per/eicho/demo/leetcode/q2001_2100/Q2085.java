package per.eicho.demo.leetcode.q2001_2100;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>2085. Count Common Words With One Occurrence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-common-words-with-one-occurrence/">2085. Count Common Words With One Occurrence</a>
 */
public final class Q2085 {
    public int countWords(String[] words1, String[] words2) {
        final Map<String, Integer> map = new HashMap<>();

        for (String word : words1) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }

        for (String word : words2) {
            if (!map.containsKey(word)) continue;
            final int count = map.get(word);
            if (count > 1 || count < 0) continue;
            map.put(word, count -1);
        }

        int result = 0;
        for (Integer count : map.values()) {
            if (count == 0) result++; 
        }

        return result;
    }
}
