package per.eicho.demo.leetcode.q301_400;

/**
 * <p>367. Valid Perfect Square 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-perfect-square/">
 *   367. Valid Perfect Square</a>
 */
public class Q367 {
    public boolean isPerfectSquare(int num) {
        // 1. 1 <= num <= 2^31 - 1
        for (int i = 0; i < 0x0000_FFFF; i++) {
            if (i * i == num)
                return true;
            if (i * i > num)
                break;
        }
        return false;
    }
}
