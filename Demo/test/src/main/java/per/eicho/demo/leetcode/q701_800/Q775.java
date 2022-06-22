package per.eicho.demo.leetcode.q701_800;

import java.util.PriorityQueue;

/**
 * <p>775. Global and Local Inversions 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/global-and-local-inversions/">
 *   775. Global and Local Inversions</a>
 */
public final class Q775 {
    public boolean isIdealPermutation(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 10^5
        // 3. 0 <= nums[i] < n
        // 4. All the integers of nums are unique.
        // 5. nums is a permutation of all the numbers in the range [0, n - 1].     
        final int n = nums.length;
        if (n <= 2) return true;
        
        final PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(Integer.MAX_VALUE);
        heap.offer(nums[n - 1]);
        heap.offer(nums[n - 2]);
        for (int i = n - 3; i >= 0; i--) {
            final int num = nums[i];
            final int numJ = nums[i + 1];
            if (num > numJ) {
                // local inversions
                if (heap.peek() != numJ) return false;
                heap.poll();
                if (num > heap.peek()) return false;
                heap.offer(numJ);
                heap.offer(num);
                continue;
            }

            // num < numJ
            if (num < heap.peek()) {
                heap.offer(num);
                continue;
            }

            // num > minNum, means has global inversions
            return false;
        }
        return true;
    }
}
