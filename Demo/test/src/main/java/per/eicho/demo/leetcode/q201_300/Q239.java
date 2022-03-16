package per.eicho.demo.leetcode.q201_300;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>239. Sliding Window Maximum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sliding-window-maximum/">
 *   239. Sliding Window Maximum</a>
 */
public final class Q239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 1 <= nums.length <= 10^5
        // -10^4 <= nums[i] <= 10^4
        // 1 <= k <= nums.length
        final int n = nums.length;
        final int[] result = new int[n - k + 1];

        // monotonic queue
        final Deque<Integer> indexInfo = new LinkedList<>();
        final Deque<Integer> monoQueue = new LinkedList<>(); // Monotonically decreasing queue

        for (int i = 0; i < k - 1; i++) {
            final int num = nums[i];

            while (!monoQueue.isEmpty() && monoQueue.peekLast() <= num) {
                monoQueue.pollLast();
                indexInfo.pollLast();
            }

            monoQueue.add(num);
            indexInfo.add(i);
        }

        int p = 0;
        for (int i = k - 1; i < n; i++) {
            final int num = nums[i];
            final int l = i - k;

            while (!monoQueue.isEmpty() && monoQueue.peekLast() <= num) {
                monoQueue.pollLast();
                indexInfo.pollLast();
            }

            monoQueue.add(num);
            indexInfo.add(i);

            while (indexInfo.peekFirst() <= l) {
                indexInfo.pollFirst();
                monoQueue.pollFirst();
            }

            result[p++] = monoQueue.peekFirst();
        }
        return result;
    }
}
