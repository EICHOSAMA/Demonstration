package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>636. Exclusive Time of Functions 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/exclusive-time-of-functions/">
 *   636. Exclusive Time of Functions</a>
 */
public final class Q636 {

    private static final class Info {
        final int methodId;
        final int startTime;
        int endTime;

        int nonExclusiveTime = 0;

        Info(int methodId, int time) {
            this.methodId = methodId;
            this.startTime = time;
        }

        int getExclusiveTime() {
            return endTime - startTime + 1 - nonExclusiveTime; 
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        // 1. 1 <= n <= 100
        // 2. 1 <= logs.length <= 500
        // 3. 0 <= function_id < n
        // 4. 0 <= timestamp <= 10^9
        // 5. No two start events will happen at the same timestamp.
        // 6. No two end events will happen at the same timestamp.
        // 7. Each function has an "end" log for each "start" log.
        final Stack<Info> callStack = new Stack<>();
        int[] result = new int[n];
        for (String log : logs) {
            int p = 0;
            char ch;
            
            // 1. parse id
            int id = 0;
            while ((ch = log.charAt(p)) != ':') {
                id = id * 10 + (ch - '0');
                p++;
            }
            p++;

            // 2. parse start/end
            boolean isStart = log.charAt(p) == 's';
            while (log.charAt(p) != ':') p++;
            p++;
            
            // 3. parse timestamp
            int timestamp = 0;
            while (p < log.length()) timestamp = timestamp * 10 + (log.charAt(p++) - '0');

            if (isStart) {
                callStack.add(new Info(id, timestamp));
            } else {
                final Info info = callStack.pop();
                info.endTime = timestamp;
                final int exclusiveTime = info.getExclusiveTime();
                result[info.methodId] += exclusiveTime;

                if (!callStack.isEmpty()) callStack.peek().nonExclusiveTime += (info.endTime - info.startTime + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q636 q636 = new Q636();
        OutputUtils.println(q636.exclusiveTime(1, Arrays.asList("0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5")));
    }
}
