package per.eicho.demo.leetcode.q601_700;

/**
 * <p>645. Set Mismatch 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/set-mismatch/">645. Set Mismatch</a>
 */
public final class Q645 {
    public int[] findErrorNums(int[] nums) {
        /*
          1. 2 <= nums.length <= 104
          2. 1 <= nums[i] <= 104
         */
        int[] counts = new int[nums.length + 1];

        for (int num : nums) {
            counts[num] += 1;
        }

        final int[] result = new int[2];

        for (int i = 1; i < counts.length; i++) {
            final int count = counts[i];

            if (count == 2) {
                result[0] = i;
            } else if (count == 0) {
                result[1] = i;
            }
        }

        return result;
    }
}
