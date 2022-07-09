package per.eicho.demo.leetcode.q901_1000;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * <p>918. Maximum Sum Circular Subarray 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-sum-circular-subarray/">
 *   918. Maximum Sum Circular Subarray</a>
 */
public final class Q918 {
    public int maxSubarraySumCircular(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 3 * 10^4
        // 3. -3 * 10^4 <= nums[i] <= 3 * 10^4
        final int n = nums.length;
        final int[] sums = genSums(nums, n);
        final int totalSum = sums[n - 1];

        final TreeMap<Integer, Set<Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            final int sum = sums[i];
            if (!treeMap.containsKey(sum)) treeMap.put(sum, new HashSet<>());
            treeMap.get(sum).add(i);
        }

        int result = treeMap.lastKey();
        for (int i = 0, offset = 0; i < n - 1; i++) {
            final int sum = sums[i];
            offset = sum;
            final Set<Integer> idxSet = treeMap.get(sum);
            idxSet.remove(i);
            if (idxSet.isEmpty()) treeMap.remove(sum);

            final int newSum = totalSum + offset;
            if (!treeMap.containsKey(newSum)) treeMap.put(newSum, new HashSet<>());
            treeMap.get(newSum).add(i);
            result = Math.max(result, treeMap.lastKey() - offset);
        }

        return result;
    }

    private int[] genSums(int[] nums, final int n) {
        final int[] sums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            sums[i] = sum;
        }
        return sums;
    }

    public static void main(String[] args) {
        Q918 q918 = new Q918();
        System.out.println(q918.maxSubarraySumCircular(new int[]{1,2,3,4,-10})); // 10
        System.out.println(q918.maxSubarraySumCircular(new int[]{5,-3,5})); // 10
        System.out.println(q918.maxSubarraySumCircular(new int[]{1,-2,3,-2})); // 3
        System.out.println(q918.maxSubarraySumCircular(new int[]{-3,-2,-3})); // -2
        
    }
}
