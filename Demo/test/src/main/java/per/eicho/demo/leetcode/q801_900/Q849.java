package per.eicho.demo.leetcode.q801_900;

/**
 * <p>849. Maximize Distance to Closest Person 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximize-distance-to-closest-person/">
 *   849. Maximize Distance to Closest Person</a>
 */
public final class Q849 {
    public int maxDistToClosest(int[] seats) {
        // 1. 2 <= seats.length <= 2 * 10^4
        // 2. seats[i] is 0 or 1.
        // 3. At least one seat is empty.
        // 4. At least one seat is occupied.
        final int n = seats.length;

        int l = 0;
        int r = n - 1;
        while (seats[l] != 1) l++;
        while (seats[r] != 1) r--;

        int result = 0;
        if (l != 0) result = Math.max(result, l);
        if (r != n - 1) result = Math.max(result, n - 1 - r);

        int p;
        while (l < r) {
            p = l + 1;
            while (seats[p] != 1) p++;
            result = Math.max(result, (p - l) / 2);
            l = p;
        }


        return result;
    }
}
