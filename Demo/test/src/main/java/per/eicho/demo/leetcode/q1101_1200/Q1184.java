package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1184. Distance Between Bus Stops 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/distance-between-bus-stops/">
 *   1184. Distance Between Bus Stops</a>
 */
public final class Q1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        // 1. 1 <= n <= 10^4
        // 2. distance.length == n
        // 3. 0 <= start, destination < n
        // 4. 0 <= distance[i] <= 10^4
        final int n = distance.length;

        // swap
        if (destination < start) {
            destination ^= start;
            start ^= destination;
            destination  ^= start;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += distance[i];
        }

        int distance1 = 0;
        for (int i = start; i < destination; i++) {
            distance1 += distance[i];
        }

        return Math.min(distance1, sum - distance1);
    }
}
