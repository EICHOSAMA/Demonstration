package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>967. Numbers With Same Consecutive Differences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/numbers-with-same-consecutive-differences/">
 *   967. Numbers With Same Consecutive Differences</a>
 */
public final class Q967 {

    List<Integer> result;
    int k;

    public int[] numsSameConsecDiff(int n, int k) {
        // 1. 2 <= n <= 9
        // 2. 0 <= k <= 9
        this.k = k;        
        result = new LinkedList<>();

        for (int i = 1; i <= 9; i++) dfs(n - 1, i, i);
        final int[] nums = new int[result.size()];
        int p = 0;
        for (int num : result) nums[p++] = num;
        return nums;
    }

    private void dfs(final int remain, final int num, final int digit) {
        if (remain == 0) {
            result.add(num);
            return;
        }

        
        int lastDigit = digit - k;
        if (lastDigit >= 0) dfs(remain - 1, num * 10 + lastDigit, lastDigit);
        if (k == 0) return;
        lastDigit = digit + k;
        if (lastDigit <= 9) dfs(remain - 1, num * 10 + lastDigit, lastDigit);
    }

    public static void main(String[] args) {
        Q967 q967 = new Q967();
        OutputUtils.println(q967.numsSameConsecDiff(3, 7));
    }
}
