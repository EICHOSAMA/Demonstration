package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1281. Subtract the Product and Sum of Digits of an Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/">
 *   1281. Subtract the Product and Sum of Digits of an Integer</a>
 */
public final class Q1281 {
    public int subtractProductAndSum(int n) {
        // 1. 1 <= n <= 10^5
        int product = 1;
        int sum = 0;
        while (n != 0) {
            final int digit = n % 10;
            n /= 10;
            product *= digit;
            sum += digit;
        }

        return product - sum;
    }
}
