package per.eicho.demo.leetcode.q1401_1500;

import java.util.TreeSet;

/**
 * <p>1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/">
 *   1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit</a>
 */
public final class Q1438 {
    public int longestSubarray(int[] nums, int limit) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 1 <= nums[i] <= 10^9
        // 3. 0 <= limit <= 10^9
        final int n = nums.length;
        int result = 1;
        final TreeSet<Integer> orderedSet = new TreeSet<>((idx1, idx2) -> {
            final int nums1 = nums[idx1];
            final int nums2 = nums[idx2];
            if (nums1 != nums2) return Integer.compare(nums1, nums2);
            return Integer.compare(idx1, idx2);
        });
        int l = 0, r = 0;
        while (l < n) {
            if (orderedSet.isEmpty()) {
                orderedSet.add(l);
                r = l;
                continue;
            }
            int first = nums[orderedSet.first()];
            int last = nums[orderedSet.last()];
            
            int num;
            while (r + 1 < n && 
                Math.abs((num = nums[r + 1]) - last) <= limit && 
                Math.abs(num - first) <= limit) {
                orderedSet.add(++r);

                first = nums[orderedSet.first()];
                last = nums[orderedSet.last()];
            }
            
            if (orderedSet.size() > result) result = orderedSet.size();
            orderedSet.remove(l++);
        }

        return result;
    }

    public static void main(String[] args) {
        Q1438 q1438 = new Q1438();
        System.out.println(q1438.longestSubarray(new int[]{8,2,4,7}, 4));
    }
}
