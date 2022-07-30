package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>916. Word Subsets 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-subsets/">
 *   916. Word Subsets</a>
 */
public final class Q916 {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // 1. 1 <= words1.length, words2.length <= 104
        // 2. 1 <= words1[i].length, words2[i].length <= 10
        // 3. words1[i] and words2[i] consist only of lowercase English letters.
        // 4. All the strings of words1 are unique.
        final int[] targetCount = new int[26];
        final int[] workspace = new int[26];
        for (String word2 : words2) {
            Arrays.fill(workspace, 0);
            for (int i = 0, len = word2.length(); i < len; i++) workspace[word2.charAt(i) - 'a']++;
            for (int i = 0; i < 26; i++) targetCount[i] = Math.max(targetCount[i], workspace[i]);
        }

        final List<String> result = new LinkedList<>();
        for (String word1 : words1) {
            Arrays.fill(workspace, 0);
            for (int i = 0, len = word1.length(); i < len; i++) workspace[word1.charAt(i) - 'a']++;
            boolean isValid = true;
            for (int i = 0; i < 26; i++) {
                if (workspace[i] < targetCount[i]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) result.add(word1);
        }

        return result;
    }
}
