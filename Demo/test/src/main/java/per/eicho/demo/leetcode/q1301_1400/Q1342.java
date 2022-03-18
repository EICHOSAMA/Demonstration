package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1342. Number of Steps to Reduce a Number to Zero 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/">
 *   1342. Number of Steps to Reduce a Number to Zero</a>
 */
public final class Q1342 {
    public int numberOfSteps(int num) {
        // 1. 0 <= num <= 10^6
        int count = 0;
        while (num != 0) {
            num = process(num);
            count++;
        }

        return count;
    }

    private int process(int num) {
        return num % 2 == 0 ? num / 2 : num - 1;
    }
}
