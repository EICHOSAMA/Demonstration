package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1523. Count Odd Numbers in an Interval Range 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/">
 *   1523. Count Odd Numbers in an Interval Range</a>
 */
public final class Q1523 {
    public int countOdds(int low, int high) {
        // 1. 0 <= low <= high <= 10^9
        return ((high + 1) / 2) - ((low + 1) / 2) + ((low % 2 == 1) ? 1 : 0);
    }
}
