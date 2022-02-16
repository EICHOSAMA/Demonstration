package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;

/**
 * <p>933. Number of Recent Calls 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-recent-calls/">933. Number of Recent Calls</a>
 */
@SuppressWarnings("unused")
public final class Q933 {
    private static final class RecentCounter {

        LinkedList<Integer> queue;

        /**
         * Initializes the counter with zero recent requests.
         */
        public RecentCounter() {
            queue = new LinkedList<>();
        }
        
        /**
         * Adds a new request at time t, where t represents some time in milliseconds, 
         * and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). 
         * Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
         * 
         * @param t
         * @return
         */
        public int ping(int t) {
            queue.add(t);

            while (!queue.isEmpty()) {
                if (t - queue.peekFirst() > 3000) {
                    queue.pollFirst();
                } else {
                    break;
                }
            }
            return queue.size();
        }
    }
}
