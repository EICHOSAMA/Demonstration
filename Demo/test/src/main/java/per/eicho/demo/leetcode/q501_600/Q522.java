package per.eicho.demo.leetcode.q501_600;

/**
 * <p>522. Longest Uncommon Subsequence II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-uncommon-subsequence-ii/">
 *   522. Longest Uncommon Subsequence II</a>
 */
public final class Q522 {
    public int findLUSlength(String[] strs) {
        // 1. 2 <= strs.length <= 50
        // 2. 1 <= strs[i].length <= 10
        // 3. strs[i] consists of lowercase English letters.
        final int n = strs.length;
        int result = -1;
        for (int i = 0, j; i < n; i++) {
            final String strI = strs[i];
            for (j = 0; j < n; j++) {
                if (j == i) continue;
                if (isSubsequence(strI, strs[j])) break;
            }
            if (j == n) result = Math.max(result, strI.length());
        }
        return result;
    }

    private boolean isSubsequence(String x, String y) {
        if (x.length() > y.length()) return false;
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++) if (x.charAt(j) == y.charAt(i)) j++;
        return j == x.length();
    }
}
