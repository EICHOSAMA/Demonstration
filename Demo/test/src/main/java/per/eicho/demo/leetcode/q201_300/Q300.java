package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>300. Longest Increasing Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-increasing-subsequence/">300. Longest Increasing Subsequence</a>
 */
public final class Q300 {
    public int lengthOfLIS(int[] nums) {
        // 1. 1 <= nums.length <= 2500
        // 2. -10^4 <= nums[i] <= 10^4
        final int n = nums.length;
        final List<Integer> minLastNums = new ArrayList<>();
        minLastNums.add(nums[0]);

        for (int i = 1; i < n; i++) {
            final int num = nums[i];

            if (num <= minLastNums.get(0)) {
                minLastNums.set(0, num);
                continue;
            }


            if (num > minLastNums.get(minLastNums.size() - 1)) {
                minLastNums.add(num);
                continue;
            } 

            // binary search
            int l = 0, r = minLastNums.size() -1;
            while (l < r) {
                final int mid = (l + r) / 2;
                final int numMid = minLastNums.get(mid);

                if (num < numMid) {
                    r = mid;
                } else {
                    // numMid < num;
                    l = mid + 1;
                }
            }

            if (minLastNums.get(r - 1) < num && num < minLastNums.get(r))
                minLastNums.set(r, num);
        }

        return minLastNums.size();
    }

    public static void main(String[] args) {
        Q300 q300 = new Q300();
        System.out.println(q300.lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }
}
