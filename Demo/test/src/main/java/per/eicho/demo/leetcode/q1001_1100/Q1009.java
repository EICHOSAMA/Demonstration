package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1009. Complement of Base 10 Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/complement-of-base-10-integer/">
 *   1009. Complement of Base 10 Integer</a>
 */
public final class Q1009 {
    public int bitwiseComplement(int n) {
        // 1. 0 <= n < 10^9
        if (n == 0) return 1;
        int biggerThanN = 1;
        while (biggerThanN <= n) biggerThanN <<= 1;
        return biggerThanN - n - 1;
    }
}
