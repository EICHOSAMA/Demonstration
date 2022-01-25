package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2068. Check Whether Two Strings are Almost Equivalent 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-whether-two-strings-are-almost-equivalent/">
 *   2068. Check Whether Two Strings are Almost Equivalent</a>
 */
public final class Q2068 {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        // 1. n == word1.length == word2.length
        // 2. 1 <= n <= 100
        // 3. word1 and word2 consist only of lowercase English letters.
        final int[] differences = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            differences[word1.charAt(i) - 'a']++;
            differences[word2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < differences.length; i++) {
            if (Math.abs(differences[i]) > 3) return false;
        }

        return true;
    }
}
