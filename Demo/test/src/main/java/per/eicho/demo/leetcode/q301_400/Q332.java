package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>332. Reconstruct Itinerary 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reconstruct-itinerary/">
 *   332. Reconstruct Itinerary</a>
 */
public final class Q332 {

    LinkedList<String> result;
    int n;
    Map<String, List<String>> graph;

    public List<String> findItinerary(List<List<String>> tickets) {
        // 1. 1 <= tickets.length <= 300
        // 2. tickets[i].length == 2
        // 3. from[i].length == 3
        // 4. to[i].length == 3
        // 5. from[i] and to[i] consist of uppercase English letters.
        // 6. from[i] != to[i]
        result = new LinkedList<>();
        n = tickets.size() + 1;
        result.add("JFK");

        graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            final String from = ticket.get(0);
            final String to = ticket.get(1);

            if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
            if (!graph.containsKey(to)) graph.put(to, new ArrayList<>());
            graph.get(from).add(to);
        }

        for (List<String> tos : graph.values()) tos.sort(String::compareTo);
        search("JFK");
        return result;
    }

    private boolean search(String city) {
        if (result.size() == n) return true;
        
        final List<String> edges = graph.get(city);
        final int size = edges.size();
        for (int i = 0; i < size; i++) {
            final String next = edges.remove(i);
            result.add(next);
            if (search(next)) return true;
            result.removeLast();
            edges.add(i, next);
        }
        return false;
    }
}
