package per.eicho.demo.leetcode.q301_400;

/**
 * <p>371. Sum of Two Integers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-two-integers/">
 *   371. Sum of Two Integers</a>
 */
public class Q371 {
    public int getSum(int a, int b) {
        // -1000 <= a, b <= 1000
        return b == 0 ? a: getSum(a ^ b, (a & b) << 1);
    }
}
