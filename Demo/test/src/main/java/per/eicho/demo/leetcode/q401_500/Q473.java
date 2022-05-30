package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>473. Matchsticks to Square 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/matchsticks-to-square/">
 *   473. Matchsticks to Square</a>
 */
public final class Q473 {

    int n;
    boolean[] mark;
    int[] matchsticks;
    int edgeLen;

    public boolean makesquare(int[] matchsticks) {
        // 1. 1 <= matchsticks.length <= 15
        // 2. 1 <= matchsticks[i] <= 10^8
        n = matchsticks.length;
        Arrays.sort(matchsticks);
        int sum = 0;
        for (int matchstick : matchsticks) sum += matchstick;
        if (sum % 4 != 0) return false;
        edgeLen = sum / 4;
        mark = new boolean[n];
        this.matchsticks = matchsticks;
        return search(0, edgeLen, n, 0);
    }

    private boolean search(int i, int remainLen, int remainMatchsticks, int idx) {
        if (remainLen == 0) return search(i + 1, edgeLen, remainMatchsticks, 0);
        if (i == 4) return remainMatchsticks == 0;

        for (int j = idx; j < n; j++) {
            if (mark[j]) continue;
            if (matchsticks[j] > remainLen) break;

            mark[j] = true;
            if (search(i, remainLen - matchsticks[j], remainMatchsticks - 1, j + 1)) return true;
            mark[j] = false;
        }
        return false;
    }
}
