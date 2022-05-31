package per.eicho.demo.leetcode.q501_600;

import java.util.List;

/**
 * <p>524. Longest Word in Dictionary through Deleting 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/">
 *   524. Longest Word in Dictionary through Deleting</a>
 */
public final class Q524 {
    public String findLongestWord(String s, List<String> dictionary) {
        // 1. 1 <= s.length <= 1000
        // 2. 1 <= dictionary.length <= 1000
        // 3. 1 <= dictionary[i].length <= 1000
        // 4. s and dictionary[i] consist of lowercase English letters.
        String longestWord = "";
        for (String word : dictionary) {
            if (word.length() < longestWord.length()) continue;
            if (!isSubsequence(s, word)) continue;
            if (word.length() > longestWord.length()) {
                longestWord = word;
            } else {
                // If there is more than one possible result, return the longest word with the smallest lexicographical order.
                if (longestWord.compareTo(word) > 0) longestWord = word;
            }
        }
        return longestWord;
    }

    private boolean isSubsequence(String s, String word) {
        int i = 0, j = 0;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                i++; j++;
            } else {
                i++;
            }
        }
        return j == word.length();
    } 
}
