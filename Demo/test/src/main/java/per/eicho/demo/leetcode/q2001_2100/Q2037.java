package per.eicho.demo.leetcode.q2001_2100;

import java.util.Arrays;

/**
 * <p>2037. Minimum Number of Moves to Seat Everyone 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/">
 *   2037. Minimum Number of Moves to Seat Everyone</a>
 */
public final class Q2037 {
    public int minMovesToSeat(int[] seats, int[] students) {
        // 1. n == seats.length == students.length
        // 2. 1 <= n <= 100
        // 3. 1 <= seats[i], students[j] <= 100
        Arrays.sort(seats);
        Arrays.sort(students);

        int result = 0;
        for (int i = 0; i < seats.length; i++) {
            result += Math.abs(seats[i] - students[i]);
        }
        
        return result;
    }
}
