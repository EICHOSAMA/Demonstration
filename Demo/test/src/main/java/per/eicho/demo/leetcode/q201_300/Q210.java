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
public final class Q210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 1 <= numCourses <= 2000
        // 2. 0 <= prerequisites.length <= numCourses * (numCourses - 1)
        // 3. prerequisites[i].length == 2
        // 4. 0 <= ai, bi < numCourses
        // 5. ai != bi
        // 6. All the pairs [ai, bi] are distinct.   

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

        final int[] result = new int[numCourses];
        int p = 0;
        while (!queue.isEmpty()) {
            final Integer course = queue.poll();
            result[p++] = course;
            
            if (!graph.containsKey(course)) continue;

            final List<Integer> edges = graph.get(course);
            for (int to : edges) {
                counts[to]--;
                if (counts[to] == 0) {
                    queue.add(to);
                }
            }
        }
        return p == numCourses ? result : null;
    }
}
