package per.eicho.demo.leetcode.q901_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>986. Interval List Intersections 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/interval-list-intersections/">
 *   986. Interval List Intersections</a>
 */
public final class Q986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // 1. 0 <= firstList.length, secondList.length <= 1000
        // 2. firstList.length + secondList.length >= 1
        // 3. 0 <= starti < endi <= 10^9
        // 4. endi < starti + 1
        // 5. 0 <= startj < endj <= 10^9
        // 6. endj < startj + 1
        final int n1 = firstList.length;
        final int n2 = secondList.length;
        int p1 = 0;
        int p2 = 0;
        final List<int[]> intervals = new ArrayList<>();
        while (p1 < n1 && p2 < n2) {
            final int[] first = firstList[p1];
            final int[] second = secondList[p2];
            
            if (second[1] < first[0]) {
                p2++;
                continue;
            }

            if (first[1] < second[0]) {
                p1++;
                continue;
            }

            intervals.add(new int[]{Math.max(first[0], second[0]), Math.min(first[1], second[1])});

            if (first[1] >= second[1] && second[1] >= first[0]) {
                p2++;
                continue;
            }

            if (second[1] >= first[1] && first[1] >= second[0]) {
                p1++;
                continue;
            }
        }

        return intervals.stream().toArray(int[][]::new);
    }
}
