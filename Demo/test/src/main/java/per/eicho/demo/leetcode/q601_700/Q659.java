package per.eicho.demo.leetcode.q601_700;

import java.util.PriorityQueue;

/**
 * <p>659. Split Array into Consecutive Subsequences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/split-array-into-consecutive-subsequences/">
 *   659. Split Array into Consecutive Subsequences</a>
 */
public final class Q659 {
    public boolean isPossible(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -1000 <= nums[i] <= 1000
        // 3. nums is sorted in non-decreasing order.
        final PriorityQueue<int[]> heap = new PriorityQueue<>((info1, info2) -> {
            // [lastNum, count]
            if (info1[0] != info2[0]) return Integer.compare(info1[0], info2[0]); // ascending-order
            return Integer.compare(info1[1], info2[1]);
        });

        final int n = nums.length;
        for (int i = 0; i < n; i++) {
            final int num = nums[i];

            if (heap.isEmpty()) {
                heap.add(new int[]{num, 1});
                continue;
            }

            if (num == heap.peek()[0]) {
                heap.add(new int[]{num, 1});
                continue;
            }

            while (!heap.isEmpty() && heap.peek()[0] + 1 < num) {
                final int[] info = heap.poll();
                if (info[1] < 3) return false; // else ok.
            }

            if (!heap.isEmpty()) {
                final int[] info = heap.poll();
                info[0] = num;
                info[1]++;
                heap.add(info);
            } else {
                heap.add(new int[]{num, 1});
            }
        }

        while (!heap.isEmpty()) {
            final int[] info = heap.poll();
            if (info[1] < 3) return false;
        }

        return true;
    }
}
