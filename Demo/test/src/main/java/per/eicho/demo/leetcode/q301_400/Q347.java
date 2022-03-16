package per.eicho.demo.leetcode.q301_400;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <p>347. Top K Frequent Elements 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/top-k-frequent-elements/">
 *   347. Top K Frequent Elements</a>
 */
public final class Q347 {
    public int[] topKFrequent(int[] nums, int k) {
        // 1 <= nums.length <= 10^5
        // k is in the range [1, the number of unique elements in the array].
        // It is guaranteed that the answer is unique.
        
        // Follow up: 
        //   Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
        final Map<Integer, Integer> counts = new HashMap<>();
        for (Integer num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }

        // 
        final PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((p1, p2) -> {
            return Integer.compare(p1.getValue(), p2.getValue());
        });
        
        for (Map.Entry<Integer, Integer> p : counts.entrySet()) {
            minHeap.add(p);
            if (minHeap.size() > k) minHeap.poll();
        }

        final int[] result = new int[k];
        int p = 0;
        while (!minHeap.isEmpty()) result[p++] = minHeap.poll().getKey();
        return result;
    }
}
