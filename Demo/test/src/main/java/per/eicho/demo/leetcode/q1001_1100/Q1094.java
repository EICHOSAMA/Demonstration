package per.eicho.demo.leetcode.q1001_1100;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>1094. Car Pooling 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/deepest-leaves-sum/">
 *   1094. Car Pooling</a>
 */
public final class Q1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        // 1. 1 <= trips.length <= 1000
        // 2. trips[i].length == 3
        // 3. 1 <= numPassengersi <= 100
        // 4. 0 <= fromi < toi <= 1000
        // 5. 1 <= capacity <= 10^5
        // trips[i] = [numPassengersi, fromi, toi]
        Arrays.sort(trips, (t1, t2) -> {
            if (t1[1] != t2[1]) return Integer.compare(t1[1], t2[1]); // ascending
            if (t1[2] != t2[2]) return Integer.compare(t1[2], t2[2]); // ascending
            return Integer.compare(t2[0], t1[0]); // descending
        });

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((t1, t2) -> {
            return Integer.compare(t1[2], t2[2]);
        });

        int p = 0;
        final int n = trips.length;
        while (p < n) {
            final int[] trip = trips[p++];

            while (!minHeap.isEmpty() && minHeap.peek()[2] <= trip[1]) {
                capacity += minHeap.poll()[0];
            }

            if (capacity < trip[0]) return false;
            capacity -= trip[0];
            minHeap.add(trip);
        }

        return true;
    }
}
