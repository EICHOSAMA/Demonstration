package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>740. Delete and Earn 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/delete-and-earn/">
 *   740. Delete and Earn</a>
 */
public final class Q740 {
    public int deleteAndEarn(int[] nums) {
        // 1. 1 <= nums.length <= 2 * 10^4
        // 2. 1 <= nums[i] <= 10^4
        final int n = nums.length;

        // 1. sort original array.
        Arrays.sort(nums);

        // 2. process original array to get the count infomation for each unique integer.
        final List<int[]> counts = getCountInfo(nums, n);

        // 3. Dynamic Programming
        final int[] f = new int[counts.size() + 1];

        for (int i = 0; i < f.length; i++) {
            // delete or not.
            final int[] info = counts.get(i);
            final int num = info[0];
            final int count = info[1];

            // option1: not delete.
            f[i + 1] = Math.max(f[i + 1], f[i]);

            // option2: delete
            // branching code can be optimized
            if ((i + 1) < counts.size()) {
                // has next.
                final int[] next = counts.get(i + 1);
                if (num + 1 == next[0]) {
                    // delete current will case next num be deleted. 
                    f[i + 2] = Math.max(f[i + 2], f[i] + num * count);
                } else {
                    f[i + 1] = Math.max(f[i + 1], f[i] + num * count);
                }
            } else {
                f[i + 1] = Math.max(f[i + 1], f[i] + num * count);
            }
        }

        return f[f.length - 1];
    }

    private List<int[]> getCountInfo(int[] nums, final int n) {
        final List<int[]> counts = new ArrayList<>();
        int currentNum = nums[0];
        int count = 1;
        int p = 0;
        while (++p < n) {
            final int num = nums[p];
            if (num == currentNum) {
                count++;
            } else {
                counts.add(new int[]{currentNum, count});
                currentNum = num;
                count = 1;
            }
        }
        counts.add(new int[]{currentNum, count});
        return counts;
    }
}
