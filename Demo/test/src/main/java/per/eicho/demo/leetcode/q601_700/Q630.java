package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>630. Course Schedule III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/course-schedule-iii/">
 *   630. Course Schedule III</a>
 */
public final class Q630 {
    public int scheduleCourse(int[][] courses) {
        // 1. 1 <= courses.length <= 10^4
        // 2. 1 <= durationi, lastDayi <= 10^4
        Arrays.sort(courses, (c1, c2) -> {
            if (c1[1] != c2[1]) return Integer.compare(c1[1], c2[1]);
            return Integer.compare(c1[0], c2[0]);
        });
        final PriorityQueue<Integer> heap = new PriorityQueue<>((d1, d2) -> Integer.compare(d2, d1));

        int time = 0;
        for (int[] course : courses) {
            final int duration = course[0];
            final int lastDay = course[1];
            if (time + duration <= lastDay) {
                time += duration;
                heap.offer(duration);
            } else {
                if (!heap.isEmpty() && heap.peek() > duration) {
                    time -= heap.poll();
                    time += duration;
                    heap.offer(duration);
                }
            }
        }
        
        return heap.size();
    }

    public static void main(String[] args) {
        Q630 q630 = new Q630();
        System.out.println(q630.scheduleCourse(new int[][]{
            new int[]{5,15},new int[]{3,19},
            new int[]{6,7},new int[]{2,10},
            new int[]{5,16},new int[]{8,14},
            new int[]{10,11},new int[]{2,19}
        }));
    }
}
