package per.eicho.demo.leetcode.q1301_1400;

import java.util.PriorityQueue;

/**
 * <p>1330. Reverse Subarray To Maximize Array Value 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/">
 *   1330. Reverse Subarray To Maximize Array Value</a>
 */
public final class Q1330 {

    private static final int MAX_VAL = 1;
    private static final int MIN_VAL = 0;

    public int maxValueAfterReverse(int[] nums) {
        // 1. 1 <= nums.length <= 3 * 10^4
        // 2. -10^5 <= nums[i] <= 10^5
        final int n = nums.length;
        final int originalValue = getOriginalValue(nums);
        if (n <= 2) return originalValue;
        final int[][] minMaxInfos = new int[n - 1][2];
        
        int p = 0;
        while (p + 1 < n) {
            final int num1 = nums[p];
            final int num2 = nums[p + 1];
            if (num1 > num2) {
                minMaxInfos[p][MAX_VAL] = num1;
                minMaxInfos[p][MIN_VAL] = num2;
            } else {
                minMaxInfos[p][MIN_VAL] = num1;
                minMaxInfos[p][MAX_VAL] = num2;    
            }

            p++;
        }

        // 1. max heap of min value.
        final PriorityQueue<int[]> maxHeap = new PriorityQueue<>((i1, i2) -> {
            return i2[MIN_VAL] - i1[MIN_VAL];
        });
        maxHeap.offer(minMaxInfos[n - 2]);

        // 2. min heap of max value.
        final PriorityQueue<int[]> minHeap = new PriorityQueue<>((i1, i2) -> {
            return i1[MAX_VAL] - i2[MAX_VAL];
        });
        minHeap.offer(minMaxInfos[n - 2]);

        int maxDiff = Integer.MIN_VALUE;
        for (int i = n - 3; i >= 0; i--) {
            final int[] minMaxInfo = minMaxInfos[i];

            int diff = maxHeap.peek()[MIN_VAL] - minMaxInfo[MAX_VAL];
            if (diff > maxDiff ) maxDiff = diff;

            diff = minMaxInfo[MIN_VAL] - minHeap.peek()[MAX_VAL];
            if (diff > maxDiff ) maxDiff = diff;

            maxHeap.offer(minMaxInfo);
            minHeap.offer(minMaxInfo);
        }

        int result = Math.max(originalValue, originalValue + 2 * maxDiff);
        
        final int num0 = nums[0];
        // Reverse  [0, i], i < n - 1
        for (int i = 1, bound = n - 1; i < bound; i++) {
            int candidate = originalValue - Math.abs(nums[i + 1] - nums[i]);
            candidate = candidate + Math.abs(nums[i + 1] - num0);
            result = Math.max(result, candidate);
        }

        final int numR = nums[n - 1];
        // Reverse [i, n - 1], i > 1
        for (int i = n - 2; i > 1; i--) {
            int candidate = originalValue - Math.abs(nums[i] - nums[i - 1]);
            candidate = candidate + Math.abs(numR - nums[i - 1]);
            result = Math.max(result, candidate);
        }
        
        return result;
    }

    private int getOriginalValue(int[] nums) {
        final int n = nums.length;
        int val = 0;
        int p = n - 1;
        while (p >= 1) {
            val += (Math.abs(nums[p] - nums[p - 1]));
            p--;
        }
        return val;
    }

    public static void main(String[] args) {
        Q1330 q1330 = new Q1330();
        // 10
        System.out.println(q1330.maxValueAfterReverse(new int[]{5,-7,9,-6,8}));
    }
}
