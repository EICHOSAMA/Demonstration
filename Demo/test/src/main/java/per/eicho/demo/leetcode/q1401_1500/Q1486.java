package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1486. XOR Operation in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/xor-operation-in-an-array/">
 *   1486. XOR Operation in an Array</a>
 */
public final class Q1486 {
    public int xorOperation(int n, int start) {
        // 1. 1 <= n <= 1000
        // 2. 0 <= start <= 1000
        // 3. n == nums.length
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= (start);
            start += 2;
        }

        return result;
    }
}
