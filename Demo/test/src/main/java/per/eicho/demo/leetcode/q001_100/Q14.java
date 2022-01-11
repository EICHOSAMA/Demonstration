package per.eicho.demo.leetcode.q001_100;

import java.util.Arrays;

/**
 * <p>14. Longest Common Prefix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-common-prefix/">14. Longest Common Prefix</a>
 */
public final class Q14 {
    private String shortestString;

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        /**
         * Find the shortest String.
         */
        int minLength = Integer.MAX_VALUE;
        shortestString = null;
        int len;
        for (String str: strs) {
            len = str.length();
            if (len < minLength) {
                shortestString = str;
                minLength = len;
            }
        }
        if (minLength == 0)
            return "";

        int result = find(strs, 0, minLength - 1);
        return shortestString.substring(0, result + 1);
    }

    /**
     * Find the longest common prefix at range [l, r]
     * @param strs
     * @param l
     * @param r
     * @return index of the longest  common prefix. is inside [l, r] or -1 if not matched.
     */
    private int find(String[] strs, int l, int r) {
        if (l > r)
            return -1;

        int mid = (l + r + 1) / 2; // inclusive
        final String prefix = shortestString.substring(0, mid + 1); // [0, mid]

        boolean isInvalid = Arrays.stream(strs)
                .parallel()
                .anyMatch(str -> !str.startsWith(prefix));

        if (isInvalid) {
            // [0, mid] is invalid common prefix.
            return find(strs, l, mid - 1);
        } else {
            // [0, mid) is valid common prefix.
            return Math.max(mid, find(strs, mid + 1, r));
        }
    }
}
