package per.eicho.demo.leetcode.q1401_1500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>1462. Course Schedule IV 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/course-schedule-iv/">
 *   1462. Course Schedule IV</a>
 */
public final class Q1462 {

    int n;
    int[][] graph;
    List<Set<Integer>> infos;

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // 1. 2 <= numCourses <= 100
        // 2. 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
        // 3. prerequisites[i].length == 2
        // 4. 0 <= ai, bi <= n - 1
        // 5. ai != bi
        // 6. All the pairs [ai, bi] are unique.
        // 7. The prerequisites graph has no cycles.
        // 8. 1 <= queries.length <= 104
        // 9. 0 <= ui, vi <= n - 1
        // 10.ui != vi
        n = numCourses;
        graph = new int[n][n];
        for (int[] preq : prerequisites) {
            final int a = preq[0];
            final int b = preq[1];
            graph[a][b] = 1;  // → son
            graph[b][a] = -1; // ← parent
        }

        infos = new ArrayList<>(n);
        for (int i = 0; i < n; i++) infos.add(null);
        for (int i = 0; i < n; i++) {
            if (infos.get(i) != null) continue;
            infos.set(i, search(i));
        }

        final List<Boolean> result = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            final int from = query[0];
            final int to = query[1];

            if (infos.get(from).contains(to)) {
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
    }

    private Set<Integer> search(int p) {
        if (infos.get(p) != null) return infos.get(p);

        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (graph[p][i] != 1) continue;

            set.add(i);
            set.addAll(search(i));
        }

        infos.set(p, set);
        return set;
    }

    public static void main(String[] args) {
        int n = 10;
        final List<Set<Integer>> infos = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            infos.add(null);
        }
    }
}
