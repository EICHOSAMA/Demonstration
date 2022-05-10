package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Q1636 {
    public int[] frequencySort(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. -100 <= nums[i] <= 100
        final int n = nums.length;
        final int[] counts = new int[201];
        final int offset = 100;
        for (int num : nums) counts[num + offset]++;

        List<Integer> result = Arrays.stream(nums)
            .boxed()
            .sorted((n1, n2) -> {
                final int count1 = counts[n1 + offset]; 
                final int count2 = counts[n2 + offset]; 
                if (count1 != count2) return Integer.compare(count1, count2); // ascending order 
                return Integer.compare(n2, n1); // decreasing order
            }).collect(Collectors.toList());
        
        for (int i = 0; i < n; i++) nums[i] = result.get(i);
        return nums;   
    }
}
