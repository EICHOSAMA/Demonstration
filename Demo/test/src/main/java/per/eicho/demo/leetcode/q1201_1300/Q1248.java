package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1248. Count Number of Nice Subarrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-number-of-nice-subarrays/">
 *   1248. Count Number of Nice Subarrays</a>
 */
public final class Q1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        // 1. 1 <= nums.length <= 50000
        // 2. 1 <= nums[i] <= 10^5
        // 3. 1 <= k <= nums.length
        final int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] %= 2;
        int result = 0;
        
        int l = 0, r = 0; // [l, r)
        int sum = 0;
        while (l < n) {
            while (r < n && sum < k) sum += nums[r++];

            if (sum != k) break;

            int countR0 = 0;
            while (r < n && nums[r] == 0) {
                r++; 
                countR0++;
            }

            while (l < r && sum == k) {
                result += (countR0 + 1);
                sum -= nums[l++];
            }
        }

        return result;
    }
}
