package per.eicho.demo.leetcode.q801_900;

import java.util.Arrays;

/**
 * <p>801. Minimum Swaps To Make Sequences Increasing 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/">
 *   801. Minimum Swaps To Make Sequences Increasing</a>
 */
public final class Q801 {
    public int minSwap(int[] nums1, int[] nums2) {
        // 1. 2 <= nums1.length <= 10^5
        // 2. nums2.length == nums1.length
        // 3. 0 <= nums1[i], nums2[i] <= 2 * 10^5
        final int n = nums1.length;
        final int[][] f = new int[n][2];
        final int defaultVal = 100_000;
        for (int[] row : f) Arrays.fill(row, 100_000);
        f[0][0] = 0;
        f[0][1] = 1;

        for (int i = 0, bound = n - 1; i < bound; i++) {
            final int[] cf = f[i];
            final int n1 = nums1[i];
            final int n2 = nums2[i];
            
            final int n3 = nums1[i + 1];
            final int n4 = nums2[i + 1];

            if (cf[0] != defaultVal) {
                if (n1 < n3 && n2 < n4) f[i + 1][0] = Math.min(f[i + 1][0], cf[0]);
                if (n1 < n4 && n2 < n3) f[i + 1][1] = Math.min(f[i + 1][1], cf[0] + 1);
            }

            if (cf[1] != defaultVal) {
                if (n2 < n3 && n1 < n4) f[i + 1][0] = Math.min(f[i + 1][0], cf[1]);
                if (n2 < n4 && n1 < n3) f[i + 1][1] = Math.min(f[i + 1][1], cf[1] + 1);    
            }
        }

        return Math.min(f[n - 1][0], f[n - 1][1]);
    }
}
