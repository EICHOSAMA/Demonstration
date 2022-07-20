package per.eicho.demo.leetcode.q1201_1300;

import java.util.PriorityQueue;

/**
 * <p>1262. Greatest Sum Divisible by Three 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/greatest-sum-divisible-by-three/">
 *   1262. Greatest Sum Divisible by Three</a>
 */
public final class Q1262 {
    public int maxSumDivThree(int[] nums) {
        // 1. 1 <= nums.length <= 4 * 10^4
        // 2. 1 <= nums[i] <= 10^4
        int result = 0;
        PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>((n1, n2) -> n2 - n1);
        PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((n1, n2) -> n2 - n1);

        for (int num : nums) {
            if (num % 3 == 0) {
                result += num;
                continue;
            }

            if (num % 3 == 1) {
                maxHeap1.offer(num);
            } else {
                maxHeap2.offer(num);
            }
        }

        if ((maxHeap1.size() - maxHeap2.size()) % 3 == 0) {
            int sum = 0;
            for (int num : nums) sum += num;
            return sum;
        }

        // swap.
        if (maxHeap1.size() < maxHeap2.size()) {
            final PriorityQueue<Integer> temp = maxHeap1;
            maxHeap1 = maxHeap2;
            maxHeap2 = temp;
        }

        // assert maxHeap1.size() > maxHeap2.size();
        while (maxHeap1.size() - maxHeap2.size() > 3) {
            result += maxHeap1.poll();
            result += maxHeap1.poll();
            result += maxHeap1.poll();
        }

        while (maxHeap1.size() != 3 && maxHeap2.size() > 0) {
            result += maxHeap1.poll();
            result += maxHeap2.poll();
        }

        if (maxHeap1.size() < 3) return result;
        int planA = result; // all in heap1
        int planB = result; // all int pair of (heap1 + heap2)
        while (maxHeap2.size() > 0) {
            final int num1 = maxHeap1.poll();
            final int num2 = maxHeap2.poll();
            planA += num1;
            planB += (num1 + num2);
        }

        while (maxHeap1.size() > 0) planA += maxHeap1.poll();

        return Math.max(planA, planB);
    }

    public static void main(String[] args) {
        Q1262 q1262 = new Q1262();
        System.out.println(q1262.maxSumDivThree(new int[]{3,6,5,1,8}));
    }
}
