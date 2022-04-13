package per.eicho.demo.leetcode.q1401_1500;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>1408. String Matching in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/string-matching-in-an-array/">
 *   1408. String Matching in an Array</a>
 */
public final class Q1408 {
    public List<String> stringMatching(String[] words) {
        // 1. 1 <= words.length <= 100
        // 2. 1 <= words[i].length <= 30
        // 3. words[i] contains only lowercase English letters.
        // 4. It's guaranteed that words[i] will be unique. 
        List<String> result = new LinkedList<>();

        for (int i = 0; i < words.length; i++) {
            final String word = words[i];

            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;

                if (words[j].length() > word.length() && words[j].contains(word)) {
                    result.add(word);
                    break;
                }
            }
        }

        return result;
    }
}
