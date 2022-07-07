package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1227. Airplane Seat Assignment Probability 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/airplane-seat-assignment-probability/">
 *   1227. Airplane Seat Assignment Probability</a>
 */
public final class Q1227 {
    public double nthPersonGetsNthSeat(int n) {
        // 1. 1 <= n <= 10^5
        return n == 1 ? 1d : 0.5d;
    }
}
