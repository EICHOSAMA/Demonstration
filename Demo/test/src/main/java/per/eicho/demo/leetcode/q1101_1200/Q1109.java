package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1109. Corporate Flight Bookings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/corporate-flight-bookings/">
 *   1109. Corporate Flight Bookings</a>
 */
public final class Q1109 {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 1. 1 <= n <= 2 * 10^4
        // 2. 1 <= bookings.length <= 2 * 10^4
        // 3. bookings[i].length == 3
        // 4. 1 <= firsti <= lasti <= n
        // 5. 1 <= seatsi <= 10^4
        final int[] result = new int[n];
        for (int[] booking : bookings) {
            final int l = booking[0];
            final int r = booking[1];
            final int count = booking[2];
            
            result[l - 1] += count;
            if (r < n) result[r] -= count;
        }
        for (int i = 1; i < n; i++) result[i] += result[i - 1];
        return result;
    }
}
