package per.eicho.demo.leetcode.q1301_1400;

import java.util.PriorityQueue;

/**
 * <p>1353. Maximum Number of Events That Can Be Attended 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/">
 *   1353. Maximum Number of Events That Can Be Attended</a>
 */
public final class Q1353 {
    
    private static final int START_DAY = 0;
    private static final int END_DAY = 1;
    
    public int maxEvents(int[][] events) {
        // 1. 1 <= events.length <= 10^5
        // 2. events[i].length == 2
        // 3. 1 <= startDayi <= endDayi <= 10^5
        final PriorityQueue<int[]> heap = new PriorityQueue<>((e1, e2) -> {
            if (e1[START_DAY] != e2[START_DAY]) return Integer.compare(e1[START_DAY], e2[START_DAY]); // ascending.
            return Integer.compare(e1[END_DAY], e2[END_DAY]); // ascending. 
        });
        int max = 0;
        for (int[] event : events) {
            heap.add(event);
            max = Math.max(max, event[END_DAY]);
        } 

        final PriorityQueue<int[]> candidates = new PriorityQueue<>((e1, e2) -> {
            if (e1[END_DAY] != e2[END_DAY]) return Integer.compare(e1[END_DAY], e2[END_DAY]); // ascending.
            return Integer.compare(e1[START_DAY], e2[START_DAY]); // ascending. don't care.
        });

        int result = 0;
        for (int day = 1, bound = max; day <= bound; day++) {
            
            while (!candidates.isEmpty() && day > candidates.peek()[END_DAY]) candidates.poll();
            while (!heap.isEmpty() && heap.peek()[START_DAY] <= day) candidates.offer(heap.poll());
            if (candidates.isEmpty()) continue; // no useable event.
            result++;
            candidates.poll();

        }
        return result;
    }

    public static void main(String[] args) {
        Q1353 q1353 = new Q1353();
        System.out.println(q1353.maxEvents(new int[][]{
            new int[]{1, 5},
            new int[]{1, 5},
            new int[]{1, 5},
            new int[]{2, 3},
            new int[]{2, 3}
        }));
    }
}
