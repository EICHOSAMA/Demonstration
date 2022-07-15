package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>957. Prison Cells After N Days 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/prison-cells-after-n-days/">
 *   957. Prison Cells After N Days</a>
 */
public final class Q957 {

    private static final int[] edges;
    private static final int[][] loopInfo;
    private static final int stateCount;

    static {
        stateCount = 256;
        edges = new int[stateCount];
        loopInfo = new int[stateCount][2]; // 0: the distance to the loop, 1: loop size.

        for (int i = 0; i < stateCount; i++) edges[i] = next(i);

        final int[] mark = new int[stateCount];
        for (int i = 0; i < stateCount; i++) {
            int p = 1;
            int state = i;

            Arrays.fill(mark, 0);
            
            mark[state] = p;
            while (p++ <= stateCount) {
                state = edges[state];
                if (mark[state] > 0) break;
                mark[state] = p;
            }

            loopInfo[i][0] = mark[state] - 1;
            loopInfo[i][1] = p - mark[state];
        }
    }

    private static int next(int state) {
        int nextState = 0b0000_0000;
        for (int i = 0, test = 0b10; i < 6; i++, test <<= 1) {
            final int r = state & (test >> 1);
            final int l = state & (test << 1);
            if (l == 0 && r == 0) {
                nextState |= test;
            } else if (l > 0 && r > 0) {
                nextState |= test;
            }
        }
        return nextState;
    }

    public int[] prisonAfterNDays(int[] cells, int n) {
        // 1. cells.length == 8
        // 2. cells[i] is either 0 or 1.
        // 3. 1 <= n <= 10^9
        int state = 0; 
        for (int i = 0; i < cells.length; i++) {
            state <<= 1;
            state += cells[i];
        }

        final int[] info = loopInfo[state];
        if (n >= info[0] + info[1]) {
            n = ((n - info[0]) % info[1]) + info[0];
        }

        while (n-- > 0) state = edges[state];

        Arrays.fill(cells, 0);
        for (int i = cells.length - 1; i >= 0; i--) {
            cells[i] = state % 2;
            state >>= 1;
        }

        return cells;
    }

    public static void main(String[] args) {
        Q957 q957 = new Q957();
        OutputUtils.println(q957.prisonAfterNDays(new int[]{0,1,0,1,1,0,0,1}, 7));
        OutputUtils.println(q957.prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000));
        
    }
}
