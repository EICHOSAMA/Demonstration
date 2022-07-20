package per.eicho.demo.leetcode.q1301_1400;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>1376. Time Needed to Inform All Employees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/time-needed-to-inform-all-employees/">
 *   1376. Time Needed to Inform All Employees</a>
 */
public final class Q1376 {

    int n;
    int[] informTime;
    Map<Integer, List<Integer>> graph; // <ManagerID, List<Subordinate ID>

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // 1. 1 <= n <= 10^5
        // 2. 0 <= headID < n
        // 3. manager.length == n
        // 4. 0 <= manager[i] < n
        // 5. manager[headID] == -1
        // 6. informTime.length == n
        // 7. 0 <= informTime[i] <= 1000
        // 8. informTime[i] == 0 if employee i has no subordinates.
        // 9 .It is guaranteed that all the employees can be informed.
        this.n = n;
        this.informTime = informTime;
        graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            final int managerID = manager[i];
            if (!graph.containsKey(managerID)) graph.put(managerID, new LinkedList<>());
            graph.get(managerID).add(i);
        }

        return dfs(headID);
    }

    private int dfs(int id) {
        if (informTime[id] == 0) return 0;
        int cost = 0;
        for (int subordinateID : graph.get(id)) cost = Math.max(cost, dfs(subordinateID));
        return cost + informTime[id];
    }
}
