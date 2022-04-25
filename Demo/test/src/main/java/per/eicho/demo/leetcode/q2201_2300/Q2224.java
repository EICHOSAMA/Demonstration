package per.eicho.demo.leetcode.q2201_2300;

/**
 * <p>2224. Minimum Number of Operations to Convert Time 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-number-of-operations-to-convert-time/">
 *   2224. Minimum Number of Operations to Convert Time</a>
 */
public final class Q2224 {
    public int convertTime(String current, String correct) {
        // 1. current and correct are in the format "HH:MM"
        // 2. current <= correct
        int ts1 = getTimestamp(current);
        int ts2 = getTimestamp(correct);
        final int[] ops = new int[]{1, 5, 15, 60};
        int p = ops.length - 1;
        int count = 0;
        while (ts1 != ts2) {
            final int diff = ts2 - ts1;
            final int time = diff / ops[p];
            count += time;
            ts1 += ops[p--] * time;
        }
        return count;
    }

    private int getTimestamp(String hhmm) {
        int h = Integer.parseInt(hhmm.substring(0, 2));
        int m = Integer.parseInt(hhmm.substring(3));
        return h * 60 + m;
    }
}
