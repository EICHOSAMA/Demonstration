package per.eicho.demo.leetcode.q501_600;

/**
 * <p>581. Shortest Unsorted Continuous Subarray 的题解代码 </p>

 * @see <a href="https://leetcode.com/problems/shortest-unsorted-continuous-subarray/">581. Shortest Unsorted Continuous Subarray</a>
 */
public final class Q581 {
    public int findUnsortedSubarray(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^5 <= nums[i] <= 10^5        
        final int n = nums.length;

        int max = Integer.MIN_VALUE, r = -1;
        int min = Integer.MAX_VALUE, l = -1;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            final int numL = nums[i];
            
            if (max > numL) {
                r = i;
            } else {
                max = numL;
            }

            final int numR = nums[j];
            if (min < numR) {
                l = j;
            } else {
                min = numR;
            }
        }
        return r == -1 ? 0 : r - l + 1;
    }

    public static void main(String[] args) {
        Q581 q581 = new Q581();
        System.out.println(q581.findUnsortedSubarray(new int[]{1,3,2,2,2})); // 4
        System.out.println(q581.findUnsortedSubarray(new int[]{1,2,3,3,3})); // 0
    }
}
