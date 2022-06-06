package per.eicho.demo.leetcode.q701_800;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * <p>752. Open the Lock 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/open-the-lock/">
 *   752. Open the Lock</a>
 */
public final class Q752 {
    public int openLock(String[] deadends, String target) {
        // 1. 1 <= deadends.length <= 500
        // 2. deadends[i].length == 4
        // 3. target.length == 4
        // 4. target will not be in the list deadends.
        // 5. target and deadends[i] consist of digits only.
        final Map<String, Integer> dis = new HashMap<>();
        for (String deadend : deadends) dis.put(deadend, -1);
        if (dis.containsKey("0000")) return -1;
        dis.put("0000", 0);
        final Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        final StringBuilder sb = new StringBuilder("0000");
        while (!queue.isEmpty() && !dis.containsKey(target)) {
            final String status = queue.poll();
            final int distance = dis.get(status);
            sb.replace(0, 4, status);

            for (int i = 0; i < 4; i++) {
                final int originalDigit = status.charAt(i) - '0';
                
                // + 1
                int newDigit = (originalDigit + 1) % 10;
                sb.setCharAt(i, (char)(newDigit + '0'));
                String nextStatus = sb.toString();
                if (!dis.containsKey(nextStatus)) {
                    dis.put(nextStatus, distance + 1);
                    queue.add(nextStatus);
                }

                // - 1
                newDigit = (originalDigit + 9) % 10; // (originalDigit - 1 + 10) % 10
                sb.setCharAt(i, (char)(newDigit + '0'));
                nextStatus = sb.toString();
                if (!dis.containsKey(nextStatus)) {
                    dis.put(nextStatus, distance + 1);
                    queue.add(nextStatus);
                }

                sb.setCharAt(i, (char)(originalDigit + '0'));
            }
        }

        return !dis.containsKey(target) ? -1 : dis.get(target);
    }
}
