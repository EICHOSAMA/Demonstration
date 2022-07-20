package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/">
 *   1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold</a>
 */
public final class Q1343 {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        // 1. 1 <= arr.length <= 10^5
        // 2. 1 <= arr[i] <= 10^4
        // 3. 1 <= k <= arr.length
        // 4. 0 <= threshold <= 10^4
        final int n = arr.length;
        int l = 0, r = 0; // [0, r)
        int sum = 0;
        final int target = threshold * k;
        int result = 0;
        while (r < n) {
            while (r < n && r - l < k) sum += arr[r++];

            if (sum >= target) result++;
            sum -= arr[l++];
        }

        return result;
    }
}
