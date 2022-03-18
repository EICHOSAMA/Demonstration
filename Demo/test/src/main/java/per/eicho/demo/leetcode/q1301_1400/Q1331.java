package per.eicho.demo.leetcode.q1301_1400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * <p>1331. Rank Transform of an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rank-transform-of-an-array/">
 *   1331. Rank Transform of an Array</a>
 */
public final class Q1331 {
    public int[] arrayRankTransform(int[] arr) {
        // 0 <= arr.length <= 10^5
        // -10^9 <= arr[i] <= 10^9        
        final List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        final PriorityQueue<Integer> minHeap = new PriorityQueue<>(list);
        int[] result = new int[arr.length];
        final Map<Integer, Integer> map = new HashMap<>();
        
        int previousRank = 1;
        while (!minHeap.isEmpty()) {
            final Integer num = minHeap.poll();
            if (map.containsKey(num)) continue;
            map.put(num, previousRank++);
        }

        for (int i = 0; i < arr.length; i++) {
            final Integer num = list.get(i);
            result[i] = map.get(num);
        }

        return result;
    }
}
