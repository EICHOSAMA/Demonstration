package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>787. Cheapest Flights Within K Stops 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/cheapest-flights-within-k-stops/">
 *   787. Cheapest Flights Within K Stops</a>
 */
public final class Q787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. 1 <= n <= 100
        // 2. 0 <= flights.length <= (n * (n - 1) / 2)
        // 3. flights[i].length == 3
        // 4. 0 <= fromi, toi < n
        // 5. fromi != toi
        // 6. 1 <= pricei <= 10^4
        // 7. There will not be any multiple flights between two cities.
        // 8. 0 <= src, dst, k < n
        // 9. src != dst

        // <City ID, List<int[]>(flight)>
        final Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            // [fromi, toi, pricei]
            final int from = flight[0];
            if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
            graph.get(from).add(flight);
        }

        int[] distances = new int[n];
        int[] newDistances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(newDistances, Integer.MAX_VALUE);
        distances[src] = 0;
        newDistances[src] = 0;

        final Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        while (!queue.isEmpty() && k >= 0) {
            for (int i = 0, size = queue.size(); i < size; i++) {
                final int from = queue.poll();
                final int totalPrice = distances[from];
                if (graph.get(from) == null) continue;

                for (int[] flight : graph.get(from)) {
                    final int to = flight[1];
                    final int price = flight[2];

                    if (totalPrice + price < newDistances[to]) {
                        newDistances[to] = totalPrice + price;
                    }
                }
            }
            
            for (int i = 0; i < n; i++) {
                if (newDistances[i] < distances[i]) {
                    distances[i] = newDistances[i];
                    queue.add(i);
                }
            }

            k--;
        }

        return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
    }
}
