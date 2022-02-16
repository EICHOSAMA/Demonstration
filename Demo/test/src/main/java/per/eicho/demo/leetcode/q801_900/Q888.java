package per.eicho.demo.leetcode.q801_900;

import java.util.Arrays;

/**
 * <p>888. Fair Candy Swap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/fair-candy-swap/">888. Fair Candy Swap</a>
 */
public final class Q888 {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        // 1 <= aliceSizes.length, bobSizes.length <= 10^4
        // 1 <= aliceSizes[i], bobSizes[j] <= 10^5
        // Alice and Bob have a different total number of candies.
        // There will be at least one valid answer for the given input.
        final int sumA = sum(aliceSizes);
        final int sumB = sum(bobSizes);
        final int diff = (sumA - sumB) / 2;
        final int[] result = new int[2];

        Arrays.sort(bobSizes);
        for (int i = 0; i < aliceSizes.length; i++) {
            final int aliceSize = aliceSizes[i];
            final int targetBobSize = aliceSize - diff;
            if (exists(bobSizes, targetBobSize)) {
                result[0] = aliceSize;
                result[1] = targetBobSize;
                break;
            }
        }
        return result;
    }

    private boolean exists(int[] sortedNums, int target) {
        int l = 0, r = sortedNums.length - 1;
        while (l < r) {
            final int mid = (l + r + 1) / 2;
            final int midNum = sortedNums[mid];

            if (target < midNum) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return sortedNums[l] == target;
    }

    private int sum(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result += num;
        }
        return result;
    } 
}
