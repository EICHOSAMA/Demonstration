package per.eicho.demo.leetcode.q001_100;

import java.util.Arrays;

/**
 * <p>16. 3Sum Closest 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/3sum-closest/">16. 3Sum Closest</a>
 */
public final class Q16 {
    public int threeSumClosest(int[] nums, int target) {
        //assert nums.length >= 3: "You may assume that each input would have exactly one solution.";

        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        
        final int BOUND_I = nums.length - 2;
        final int BOUND_J = nums.length - 1;
        for (int i = 0; i < BOUND_I; i++) {
            final int numI = nums[i];
            for (int j = i + 1; j < BOUND_J; j++) {
                final int numJ = nums[j];

                int closestNum = binarySearchClosestNum(nums, target - numI - numJ, j + 1, BOUND_J); // inclusive: [j+1, BOUND_J]
                if (Math.abs(target - result) > Math.abs(target - numI - numJ - closestNum)) {
                    result = numI + numJ + closestNum; // update
                }
                
                if (result == target) return result;
            }
        }

        return result;
    }

    private int binarySearchClosestNum(
            final int[] nums,
            final int target,
            final int l,
            final int r) {
        if (l == r) return nums[l]; // boundary condition for searching right.
        if (l + 1 == r) {
            // boundary condition for searching left.
            if (Math.abs(nums[r] - target) < Math.abs(target - nums[l])) return nums[r]; // closest.
            return nums[l];
        }

        final int mid = (l + r + 1) / 2;

        final int numMid = nums[mid];

        if (target >= numMid)
            return binarySearchClosestNum(nums, target, mid, r); // search right.
        return binarySearchClosestNum(nums, target, l, mid); // search left.
    }
}
