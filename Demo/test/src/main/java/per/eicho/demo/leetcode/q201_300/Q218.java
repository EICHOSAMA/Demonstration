package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <p>218. The Skyline Problem 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/the-skyline-problem/">218. The Skyline Problem</a>
 */
public final class Q218 {

    final static int HEIGHT = 2;

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 1. 1 <= buildings.length <= 10^4
        // 2. 0 <= [lefti] < right[i] <= 2^31 - 1
        // 3. 1 <= height[i] <= 2^31 - 1
        // 4. buildings is sorted by left[i] in non-decreasing order.        
        final PriorityQueue<int[]> heap = new PriorityQueue<int[]>((b1, b2) -> Integer.compare(b2[HEIGHT], b1[HEIGHT]));
        final List<Integer> boundaries = new ArrayList<Integer>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        final List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            // 1. maintain heap.
            // 1.1 add all informations that may be used to the heap.
            while (idx < n && buildings[idx][0] <= boundary) {
                heap.offer(buildings[idx]);
                idx++;
            }

            // 1.2 remove useless information from heap.
            while (!heap.isEmpty() && heap.peek()[1] <= boundary) heap.poll();

            // 2. get max height info from heap.
            final int maxHeight = heap.isEmpty() ? 0 : heap.peek()[HEIGHT];
            
            // 3. check whether can be add to result. (same height should be merged ( witch means skip))
            if (result.size() == 0 || maxHeight != result.get(result.size() - 1).get(1)) {
                result.add(Arrays.asList(boundary, maxHeight));
            }
        }
        return result;
    }
}
