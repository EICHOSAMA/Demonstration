package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>207. Course Schedule 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a>
 */
public final class Q207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 1 <= numCourses <= 10^5
        // 2. 0 <= prerequisites.length <= 5000
        // 3. prerequisites[i].length == 2
        // 4. 0 <= ai, bi < numCourses
        // 5. All the pairs prerequisites[i] are unique.        

        // 1. count prerequestites for each course.
        // 2. build graph.
        final int[] counts = new int[numCourses];
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : prerequisites) {
            final Integer from = pair[1];
            final Integer to = pair[0];

            counts[to]++;
            if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        // 3. get all courses with 0 entry.
        final Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) queue.add(i);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            final Integer course = queue.poll();
            count++;
            
            if (!graph.containsKey(course)) continue;

            final List<Integer> edges = graph.get(course);
            for (int to : edges) {
                counts[to]--;
                if (counts[to] == 0) {
                    queue.add(to);
                }
            }
        }
        return count == numCourses;
    }
}
