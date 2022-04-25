package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2169. Count Operations to Obtain Zero 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-operations-to-obtain-zero/">
 *   2169. Count Operations to Obtain Zero</a>
 */
public final class Q2169 {
    public int countOperations(int num1, int num2) {
        // 0 <= num1, num2 <= 10^5
        int count = 0;
        while (num1 != 0 && num2 != 0) {
            count++;
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }
        return count;
    }
}
