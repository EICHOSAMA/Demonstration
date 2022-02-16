package per.eicho.demo.leetcode.q1001_1100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>989. Add to Array-Form of Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-to-array-form-of-integer/">989. Add to Array-Form of Integer</a>
 */
public final class Q1002 {
    public List<String> commonChars(String[] words) {
        // 1. 1 <= words.length <= 100
        // 2. 1 <= words[i].length <= 100
        // 3. words[i] consists of lowercase English letters.        
        final int[][] counts = new int[words.length][26];
        final int[] min = new int[26];
        Arrays.fill(min, Integer.MAX_VALUE);

        for (int i = 0; i < words.length; i++) {
            final int[] count = counts[i];
            final String word = words[i];

            for (int j = 0; j < word.length(); j++) {
                final char ch = word.charAt(j);
                count[ch - 'a']++;
            }

            for (int j = 0; j < 26; j++) {
                min[j] = Math.min(min[j], count[j]);
            }
        }

        final List<String> result = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < min[i]; j++) {
                result.add("" + ((char)(i + 'a')));
            }
        }
        return result;
    }
}
