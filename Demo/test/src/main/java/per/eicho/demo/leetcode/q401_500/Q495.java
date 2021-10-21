package per.eicho.demo.leetcode.q401_500;

/**
 * <p>495. Teemo Attacking 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/teemo-attacking/">495. Teemo Attacking</a>
 */
final public class Q495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        /*
          1 <= timeSeries.length <= 104
          0 <= timeSeries[i], duration <= 107
          timeSeries is sorted in non-decreasing order.
        */
        int result = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            final int d = timeSeries[i + 1] - timeSeries[i];
            result += (d <= duration) ? d : duration;
        }
        result += duration; // add last damage duration time.
        return result;
    }
}
