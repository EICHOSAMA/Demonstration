package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;

/**
 * <p>611. Valid Triangle Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-triangle-number/">
 *   611. Valid Triangle Number</a>
 */
public final class Q611 {
    public int triangleNumber(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 0 <= nums[i] <= 1000
        final int n = nums.length;
        Arrays.sort(nums);

        int result = 0;
        for (int i = 0; i < n - 2; i++) {
            final int a = nums[i];
            if (a == 0) continue;
            for (int j = i + 1; j < n - 1; j++) {
                final int b = nums[j];
                if (b == 0) continue;
                final int target = a + b;
                if (nums[j + 1] >= target) continue;
                result += (binarySearch(nums, j + 1, n - 1, target) - j);
            }
        }
        return result;
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        if (l == r) return l;
        final int mid = (l + r + 1) / 2;
        final int numMid = nums[mid];

        if (numMid < target) return binarySearch(nums, mid, r, target);
        // numMid >= target;
        return binarySearch(nums, l, mid - 1, target);
    }

    public static void main(String[] args) {
        Q611 q611 = new Q611();
        System.out.println(q611.triangleNumber(new int[]{0,1,2,3}));
    }
}
