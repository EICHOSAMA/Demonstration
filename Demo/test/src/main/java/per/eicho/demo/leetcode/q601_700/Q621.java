package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <p>621. Task Scheduler 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/task-scheduler/">621. Task Scheduler</a>
 */
public final class Q621 {
    public int leastInterval(char[] tasks, int n) {
        // 1. 1 <= task.length <= 10^4
        // 2. tasks[i] is upper-case English letter.
        // 3. The integer n is in the range [0, 100].        
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((info1, info2) -> {
            if (info1[1] != info2[1]) return Integer.compare(info2[1], info1[1]);
            return Integer.compare(info1[0], info2[0]);
        });

        final int[] counts = new int[26];
        for (char task : tasks) counts[task - 'A']++;
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                maxHeap.add(new int[]{i, counts[i]});
            }
        }

        int result = 0;
        final List<int[]> colldownTasks = new ArrayList<>(26);
        n++;
        while (!maxHeap.isEmpty()) {
            int count = n;
            System.out.println(maxHeap.size());
            while (count > 0 && !maxHeap.isEmpty()) {
                final int[] task = maxHeap.poll();
                if (--task[1] > 0) colldownTasks.add(task);
                count--;
            }

            if (colldownTasks.size() == 0) {
                result += (n - count);
            } else {
                result += n;
                maxHeap.addAll(colldownTasks);
                colldownTasks.clear();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q621 q621 = new Q621();
        System.out.println(q621.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }
}
