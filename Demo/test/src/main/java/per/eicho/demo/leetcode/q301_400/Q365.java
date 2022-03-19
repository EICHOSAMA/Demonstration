package per.eicho.demo.leetcode.q301_400;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * <p>365. Water and Jug Problem 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/water-and-jug-problem/">
 *   365. Water and Jug Problem</a>
 */
public final class Q365 {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        // 1. 1 <= jug1Capacity, jug2Capacity, targetCapacity <= 10^6
        return canMeasureWater((long)jug1Capacity, (long)jug2Capacity, (long)targetCapacity);
    }

    private static final long OFFSET = 10_000_000;

    private boolean canMeasureWater(long jug1Capacity, long jug2Capacity, long targetCapacity) {
        
        final Queue<Long> queue = new LinkedList<>();
        queue.add(0L);

        final Set<Long> marks = new HashSet<>();
        marks.add(0L);

        while (!queue.isEmpty()) {
            final Long status = queue.poll();

            final long j1 = status / OFFSET;
            final long j2 = status % OFFSET;
            if (j1 + j2 == targetCapacity) return true;

            // Fill any of the jugs with water.
            long s1 = statusCode(jug1Capacity, j2);
            long s2 = statusCode(j1, jug2Capacity);

            // Empty any of the jugs.
            long s3 = statusCode(0, j2);
            long s4 = statusCode(j1, 0);

            // Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.
            long sum = j1 + j2;
            
            long s5 = statusCode(sum >= jug1Capacity ? jug1Capacity : sum  , sum <= jug1Capacity ? 0 : sum - jug1Capacity); // ← left
            long s6 = statusCode(sum <= jug2Capacity ? 0 : sum - jug2Capacity , sum >= jug2Capacity ? jug2Capacity : sum);

            addIFAbsent(s1, marks, queue);
            addIFAbsent(s2, marks, queue);
            addIFAbsent(s3, marks, queue);
            addIFAbsent(s4, marks, queue);
            addIFAbsent(s5, marks, queue);
            addIFAbsent(s6, marks, queue);
        }

        return false;
    }

    private long statusCode(long j1, long j2) { return j1 * OFFSET + j2; }
    private void addIFAbsent(Long code, Set<Long> marks, Queue<Long> queue) {
        if (marks.contains(code)) return;

        marks.add(code);
        queue.add(code);
    }
}
