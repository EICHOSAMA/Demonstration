package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1662. Check If Two String Arrays are Equivalent 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/">
 *   1662. Check If Two String Arrays are Equivalent</a>
 */
public final class Q1662 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // 1. 1 <= word1.length, word2.length <= 10^3
        // 2. 1 <= word1[i].length, word2[i].length <= 10^3
        // 3. 1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
        // 4. word1[i] and word2[i] consist of lowercase letters.
        final int[] idx1 = new int[]{0, 0};
        final int[] idx2 = new int[]{0, 0};
        
        while (true) {
            if (word1[idx1[0]].charAt(idx1[1]) != word2[idx2[0]].charAt(idx2[1])) return false;

            if (++idx1[1] == word1[idx1[0]].length()) {
                idx1[0]++;
                idx1[1] = 0;
            }

            if (++idx2[1] == word2[idx2[0]].length()) {
                idx2[0]++;
                idx2[1] = 0;
            }

            if (idx1[0] == word1.length || idx2[0] == word2.length) break;
        }

        return idx1[0] == word1.length && idx2[0] == word2.length;
    }
}
