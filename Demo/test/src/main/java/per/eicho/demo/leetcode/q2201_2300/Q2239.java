package per.eicho.demo.leetcode.q2201_2300;

/**
 * <p>2239. Find Closest Number to Zero 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-closest-number-to-zero/">
 *   2239. Find Closest Number to Zero</a>
 */
public final class Q2239 {
    public int findClosestNumber(int[] nums) {
        // 1. 1 <= n <= 1000
        // 2. -10^5 <= nums[i] <= 10^5 
        int result = nums[0];
        int distance = Math.abs(result);
        for (int num : nums) {
            final int dis = Math.abs(num); 
            if (dis > distance) continue;
            if (dis < distance) {
                distance = dis;
                result = num;
                continue;
            }

            if (num > result) result = num;
        }

        return result;
    }
}
