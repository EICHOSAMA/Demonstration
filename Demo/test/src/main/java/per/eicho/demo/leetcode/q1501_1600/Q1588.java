package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1588. Sum of All Odd Length Subarrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-all-odd-length-subarrays/">
 *   1588. Sum of All Odd Length Subarrays</a>
 */
public final class Q1588 {
    public int sumOddLengthSubarrays(int[] arr) {
        // 1. 1 <= arr.length <= 100
        // 2. 1 <= arr[i] <= 1000        
        final int n = arr.length;
        int sum = 0;
        for (int len = 1; len <= n ; len += 2) {
            for (int i = 0; i + len - 1 < n; i++) {
                for (int j = i; j < i + len; j++) {
                    sum += arr[j];
                }
            }    
        }
        return sum;
    }
}
