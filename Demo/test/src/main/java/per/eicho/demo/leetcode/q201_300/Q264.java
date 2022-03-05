package per.eicho.demo.leetcode.q201_300;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <p>264. Ugly Number II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/ugly-number-ii/">
 *   264. Ugly Number II</a>
 */
public final class Q264 {
    public int nthUglyNumber(int n) {
        // 1. 1 <= n <= 1690
        final PriorityQueue<Long> heap = new PriorityQueue<>();
        final Set<Long> mark = new HashSet<>();
        heap.add(1L);
        mark.add(1L);

        final long[] uglyNums = new long[n];

        int p = 0;
        while (p < n) {
            final long uglyNum = heap.poll();
            uglyNums[p++] = uglyNum;

            final Long u2 = (long)uglyNum * 2L;
            if (!mark.contains(u2)) {
                mark.add(u2);
                heap.add(u2);
            }

            final Long u3 = (long)uglyNum * 3L;
            if (!mark.contains(u3)) {
                mark.add(u3);
                heap.add(u3);
            }

            final Long u5 = (long)uglyNum * 5L;
            if (!mark.contains(u5)) {
                mark.add(u5);
                heap.add(u5);
            }
        }
        return (int)uglyNums[n - 1];
    }
}
