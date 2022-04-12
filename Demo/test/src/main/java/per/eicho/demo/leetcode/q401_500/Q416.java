package per.eicho.demo.leetcode.q401_500;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>416. Partition Equal Subset Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partition-equal-subset-sum/">416. Partition Equal Subset Sum</a>
 */
public final class Q416 {
    public boolean canPartition(int[] nums) {
        // 1. 1 <= nums.length <= 200
        // 2. 1 <= nums[i] <= 100
        int sum = 0;
        for (int num : nums) sum += num;
        
        if (sum % 2 != 0) return false;

        final int half = sum / 2;
        Set<Integer> possibleSums = new HashSet<>(100);
        possibleSums.add(0);
        Set<Integer> nextPossibleSums = new HashSet<>(100);

        for (int num : nums) {
            if (num > half) return false;

            for (Integer possibleSum : possibleSums) {
                nextPossibleSums.add(possibleSum);
                if (possibleSum + num <= half) nextPossibleSums.add(possibleSum + num);
            }

            possibleSums = nextPossibleSums;
            nextPossibleSums = new HashSet<>(possibleSums.size());
        }
        return possibleSums.contains(half);
    }
}
