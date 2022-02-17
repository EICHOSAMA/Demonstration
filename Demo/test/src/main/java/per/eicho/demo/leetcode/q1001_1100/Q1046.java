package per.eicho.demo.leetcode.q1001_1100;

import java.util.PriorityQueue;

/**
 * <p>1046. Last Stone Weight 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/last-stone-weight/">
 *   1046. Last Stone Weight</a>
 */
public final class Q1046 {
    public int lastStoneWeight(int[] stones) {
        // 1. 1 <= stones.length <= 30
        // 2. 1 <= stones[i] <= 1000        

        final PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1)); 
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() >= 2) {
            int s1 = maxHeap.poll();
            int s2 = maxHeap.poll();

            if (s1 == s2) continue;
            maxHeap.add(s1 - s2);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}
