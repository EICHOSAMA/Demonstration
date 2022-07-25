package per.eicho.demo.leetcode.q1401_1500;

import java.util.Arrays;
import per.eicho.demo.leetcode.debug.InputUtils;

/**
 * <p>1494. Parallel Courses II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/parallel-courses-ii/">
 *   1494. Parallel Courses II</a>
 */
public final class Q1494 {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        // 1. 1 <= n <= 15
        // 2. 1 <= k <= n
        // 3. 0 <= relations.length <= n * (n-1) / 2
        // 4. relations[i].length == 2
        // 5. 1 <= prevCoursei, nextCoursei <= n
        // 6. prevCoursei != nextCoursei
        // 7. All the pairs [prevCoursei, nextCoursei] are unique.
        // 8. The given graph is a directed acyclic graph.
        final int[] pre = new int[n];
        for(int[] relation : relations){
            final int from = relation[0] - 1;
            final int to = relation[1] - 1;
            pre[to] |= (1 << from);
        }
        
        int statusCount = 1 << n; // 2^n
        final int[] f = new int[statusCount];
        Arrays.fill(f, n);
        f[0] = 0;

        for(int status = 0; status < statusCount; status++){
            int waitStudy = 0;
            for(int i = 0; i < n; i++){
                if(!canStudy(status, pre[i])) continue;
                waitStudy |= 1 << i;
            }

            waitStudy = waitStudy & (~status);

            for(int target = waitStudy; target > 0; target = (target - 1) & waitStudy){
                if(Integer.bitCount(target) > k) continue;
                final int nextStatus = status|target;
                f[nextStatus] = Math.min(f[nextStatus], f[status] + 1);
            }
        }
        return f[statusCount - 1];
    }

    private boolean canStudy(int status, int pre) {
        return (status & pre) == pre;
    }

    public static void main(String[] args) {
        Q1494 q1494 = new Q1494();
        System.out.println(q1494.minNumberOfSemesters(12, 
            InputUtils.gen2DArray("[[1,2],[1,3],[7,5],[7,6],[4,8],[8,9],[9,10],[10,11],[11,12]]"), 
            2));
    }
}
