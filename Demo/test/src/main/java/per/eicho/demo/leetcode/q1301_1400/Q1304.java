package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1304. Find N Unique Integers Sum up to Zero 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/">
 *   1304. Find N Unique Integers Sum up to Zero</a>
 */
public final class Q1304 {
    public int[] sumZero(int n) {
        // 1 <= n <= 1000
        final int[] result = new int[n];
        
        int sum = 0;
        int num = 0;
        for (int i = 0; i < n - 1; i++) {
            result[i] = num++;
            sum += result[i];
        }
        result[n - 1] = -sum;
        return result;
    }
}
