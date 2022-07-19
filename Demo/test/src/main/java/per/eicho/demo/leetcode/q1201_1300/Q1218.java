package per.eicho.demo.leetcode.q1201_1300;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>1218. Longest Arithmetic Subsequence of Given Difference 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/">
 *   1218. Longest Arithmetic Subsequence of Given Difference</a>
 */
public final class Q1218 {
    public int longestSubsequence(int[] arr, int difference) {
        // 1. 1 <= arr.length <= 10^5
        // 2. -10^4 <= arr[i], difference <= 10^4
        int result = 1;
        final Map<Integer, Integer> map = new HashMap<>();
        final int defaultVal = difference == 0 ? 0 : 1;
        for (int num : arr) {
            map.putIfAbsent(num, defaultVal);

            if (!map.containsKey(num - difference)) continue;
            final int newLen = map.get(num - difference) + 1;
            map.put(num, newLen);
            result = Math.max(result, newLen);
        }

        return result;
    }

    public static void main(String[] args) {
        Q1218 q1218 = new Q1218();
        System.out.println(q1218.longestSubsequence(new int[]{4,12,10,0,-2,7,-8,9,-9,-12,-12,8,8}, 0));
    }
}
