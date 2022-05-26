package per.eicho.demo.leetcode.q101_200;

/**
 * <p>191. Number of 1 Bits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-1-bits/">
 *   191. Number of 1 Bits</a>
 */
public final class Q191 {
    public int hammingWeight(int n) {
        // 1. The input must be a binary string of length 32.
        int result = 0;
        while (n != 0) {
            if ((n & -n) == 1) result++;
            n >>>= 1;
        }
        return result;
    }
}
