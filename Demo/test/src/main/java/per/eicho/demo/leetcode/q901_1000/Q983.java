package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;

/**
 * <p>983. Minimum Cost For Tickets 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-cost-for-tickets/">
 *   983. Minimum Cost For Tickets</a>
 */
public final class Q983 {
    public int mincostTickets(int[] days, int[] costs) {
        // 1. 1 <= days.length <= 365
        // 2. 1 <= days[i] <= 365
        // 3. days is in strictly increasing order.
        // 4. costs.length == 3
        // 5. 1 <= costs[i] <= 1000
        final int n = days.length;
        final int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;

        for (int i = 0; i < n; i++) {
            final int l = days[i];
                    
            int fi = f[i];

            int p = i;
            while (p < n && days[p] - l < 1) {
                f[p + 1] = Math.min(f[p + 1], fi + costs[0]);
                p++;
            }

            p = i;
            while (p < n && days[p] - l < 7) {
                f[p + 1] = Math.min(f[p + 1], fi + costs[1]);
                p++;
            }

            p = i;
            while (p < n && days[p] - l < 30) {
                f[p + 1] = Math.min(f[p + 1], fi + costs[2]);
                p++;
            }
        }

        return f[n];
    }

    public static void main(String[] args) {
        Q983 q983 = new Q983();
        System.out.println(q983.mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
    }
}
