package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2073. Time Needed to Buy Tickets 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/time-needed-to-buy-tickets/">
 *   2073. Time Needed to Buy Tickets</a>
 */
public final class Q2073 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        // 1. n == tickets.length
        // 2. 1 <= n <= 100
        // 3. 1 <= tickets[i] <= 100
        // 4. 0 <= k < n
        final int n = tickets.length;

        int time = 0;
        int p = 0;
        while (tickets[k] != 0) {
            if (tickets[p] == 0) {
                p = next(p, n);
                continue;
            }
            tickets[p]--;
            p = next(p, n);
            time++;
        }

        return time;
    }

    private int next(int p, int n) {
        return ++p % n;
    }
}
