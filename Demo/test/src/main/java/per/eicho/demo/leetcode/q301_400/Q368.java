package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>368. Largest Divisible Subset 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-divisible-subset/">
 *   368. Largest Divisible Subset</a>
 */
public final class Q368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 1 <= nums[i] <= 2 * 10^9
        // 3. All the integers in nums are unique.
        final int n = nums.length;
        Arrays.sort(nums);

        final int[] f = new int[n];
        final int[] prev = new int[n];
        Arrays.fill(f, 1);
        Arrays.fill(prev, -1);

        final int maxNum = nums[n - 1];
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            final int num = nums[i];

            final int remain = n - i - 1;
            final int time = maxNum / num;
            
            if (remain < time) {
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] % num != 0) continue;
                    
                    if (f[j] < f[i] + 1) {
                        f[j] = f[i] + 1;
                        prev[j] = i;
                    }
                }
            } else {
                for (int j = 2; j <= time; j++) {
                    final int targetNum = num * j;
                    final int idx = binarySearch(nums, i + 1, n - 1, targetNum); 
                    if (idx == -1) continue;
    
                    if (f[idx] < f[i] + 1) {
                        f[idx] = f[i] + 1;
                        prev[idx] = i;
                    }
                }
            }

            if (f[i] > f[maxIdx]) maxIdx = i;
        }

        final List<Integer> result = new ArrayList<>(f[maxIdx]);
        int cursor = maxIdx;
        while (cursor != -1) {
            result.add(nums[cursor]);
            cursor = prev[cursor];
        }

        return result;
    }

    private int binarySearch(int[] sortedNums, int l, int r, int targetNum) {
        if (l > r) return -1;
        if (l == r) return sortedNums[l] == targetNum ? l : -1;

        final int mid = (l + r + 1) / 2;
        final int midNum = sortedNums[mid];

        if (targetNum < midNum) return binarySearch(sortedNums, l, mid - 1, targetNum);
        return binarySearch(sortedNums, mid, r, targetNum);
    }
}
