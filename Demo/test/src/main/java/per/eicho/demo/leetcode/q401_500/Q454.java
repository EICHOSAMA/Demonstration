package per.eicho.demo.leetcode.q401_500;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>454. 4Sum II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/4sum-ii/">
 *   454. 4Sum II</a>
 */
public final class Q454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 1. n == nums1.length
        // 2. n == nums2.length
        // 3. n == nums3.length
        // 4. n == nums4.length
        // 5. 1 <= n <= 200
        // 6. -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
        final Map<Integer, int[]> countInfo = new HashMap<>();
        final int n = nums1.length;

        for (int i = 0; i < n; i++) {
            final int num3 = nums3[i];
            for (int j = 0; j < n; j++) {
                final Integer sum = nums4[j] + num3;
                if (!countInfo.containsKey(sum)) countInfo.put(sum, new int[]{0});
                countInfo.get(sum)[0]++;
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            final int num1 = nums1[i];
            for (int j = 0; j < n; j++) {
                final Integer target = -nums2[j] - num1;
                if (countInfo.containsKey(target)) {
                    result += countInfo.get(target)[0];
                }
            }
        }

        return result;
    }
}
