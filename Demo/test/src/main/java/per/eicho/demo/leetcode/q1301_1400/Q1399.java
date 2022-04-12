package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1399. Count Largest Group 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-largest-group/">
 *   1399. Count Largest Group</a>
 */
public final class Q1399 {
    public int countLargestGroup(int n) {
        // 1 <= n <= 10^4
    
        final int[] counts = new int[37]; // 9+9+9+9

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            int num = i;
            while (num != 0) {
                sum += (num % 10);
                num /= 10;
            }
            counts[sum]++;
        }

        int max = 0;
        for (int i = 0; i < 37; i++) max = Math.max(max, counts[i]);

        int result = 0;
        for (int i = 0; i < 37; i++) if (counts[i] == max) result++;
        return result;
    }
}
