package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1550. Three Consecutive Odds 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/three-consecutive-odds/">
 *   1550. Three Consecutive Odds</a>
 */
public final class Q1550 {
    public boolean threeConsecutiveOdds(int[] arr) {
        // 1. 1 <= arr.length <= 1000
        // 2. 1 <= arr[i] <= 1000
        final int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            if (arr[i] % 2 == 0) continue;
            if (arr[++i] % 2 == 0) continue;
            if (arr[++i] % 2 == 0) continue;
            return true; 
        }
        
        return false;
    }
}
