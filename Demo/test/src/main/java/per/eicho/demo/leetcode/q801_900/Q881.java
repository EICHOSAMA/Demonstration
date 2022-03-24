package per.eicho.demo.leetcode.q801_900;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <p>881. Boats to Save People 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/boats-to-save-people/">881. Boats to Save People</a>
 */
public final class Q881 {
    public int numRescueBoats(int[] people, int limit) {
        // 1. 1 <= people.length <= 5 * 10^4
        // 2. 1 <= people[i] <= limit <= 3 * 10^4
        Arrays.sort(people); // ASE 
        int l = 0, r = people.length - 1;
        int result = 0;
        while (l <= r) {
            if (people[l] + people[r] <= limit) l++; // 2people
            r--;
            result++;
        }
        return result;
    }
}
