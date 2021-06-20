package per.eicho.demo.leetcode.q501_600;

/**
 * <p>507. Perfect Number 的题解代码 </p>
 * 
 * <pre>
 *  A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself. 
 *  A divisor of an integer x is an integer that can divide x evenly.
 * 
 * Example 1: 
 *    Input: num = 28
 *    Output: true
 *    Explanation: 28 = 1 + 2 + 4 + 7 + 14
 *    1, 2, 4, 7, and 14 are all divisors of 28.
 * 
 * Example 2:
 *    Input: root = []
 *    Output: []
 * 
 * Example 3:
 *    Input: root = [1]
 *    Output: [1]
 * 
 * Example 4:
 *    Input: root = [1,2]
 *    Output: [2,1]
 * 
 * Example 5:
 *    Input: root = [1,null,2]
 *    Output: [2,1]
 * 
 *  Constraints:
 *    1. 1 <= num <= 108
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/perfect-number/">507. Perfect Number</a>
 */
final public class Q507 {
    public boolean checkPerfectNumber(int num) {
        //assert 1 <= num && num <= 100000000;
        final int goal = num * 2;
        int sum = 0;

        int j = num;
        for (int i = 1; i < j; i++) {
            if (num % i == 0) {
                j = num / i;
                
                sum += i;
                sum += j;
                System.out.println(sum);

                // [performace optimization]
                if (sum > goal) {
                    return false;
                }
            }
        }

        return sum == goal;
    }

    public static void main(String[] args) {
        final Q507 q507 = new Q507();

        System.out.println(q507.checkPerfectNumber(28));
    }
}
