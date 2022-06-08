package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>667. Beautiful Arrangement II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/beautiful-arrangement-ii/">
 *   667. Beautiful Arrangement II</a>
 */
public final class Q667 {
    public int[] constructArray(int n, int k) {
        // 1. 1 <= k < n <= 10^4
        final int[] result = new int[n];
        result[0] = 1;
        int l = 2, r = n;
        int p = 1;
        while (k > 1) {
            if (p % 2 == 0) {
                result[p++] = l++;
            } else {
                result[p++] = r--;
            }
            k--;
        }

        if (result[p - 1] == l - 1) {
            while (p < n) result[p++] = l++;
        } else {
            while (p < n) result[p++] = r--;
        }

        return result;
    }

    public static void main(String[] args) {
        Q667 q667 = new Q667();
        OutputUtils.println(q667.constructArray(3, 2)); // [1,3,2]
        OutputUtils.println(q667.constructArray(3, 1)); // [1,2,3]
        
    }
}
