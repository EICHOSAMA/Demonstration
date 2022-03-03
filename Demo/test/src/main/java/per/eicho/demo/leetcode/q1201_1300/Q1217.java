package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1217. Minimum Cost to Move Chips to The Same Position 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/">
 *   1217. Minimum Cost to Move Chips to The Same Position</a>
 */
public final class Q1217 {
    public int minCostToMoveChips(int[] position) {
        // 1. 1 <= position.length <= 100
        // 2. 1 <= position[i] <= 10^9        
        final int n = position.length;
        int odd = 0;
        for (int num : position) {
            if (num % 2 != 0) odd++;
        }
        return Math.min(odd, n - odd);
    }
}
