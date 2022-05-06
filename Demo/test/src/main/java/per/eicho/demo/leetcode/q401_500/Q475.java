package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>475. Heaters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/heaters/">
 *   475. Heaters</a>
 */
public final class Q475 {
    public int findRadius(int[] houses, int[] heaters) {
        // 1. 1 <= houses.length, heaters.length <= 3 * 10^4
        // 2. 1 <= houses[i], heaters[i] <= 10^9        
        Arrays.sort(houses);
        Arrays.sort(heaters);
        final int n = houses.length;
        final int m = heaters.length;

        int radius = 0;

        int j = 0;
        for (int i = 0; i < n; i++) {
            final int house = houses[i];

            while (j < m && heaters[j] < house) j++;
            
            if (j == 0) {
                radius = Math.max(radius, Math.abs(heaters[j] - house));
            } else if (j == m) {
                radius = Math.max(radius, Math.abs(heaters[j - 1] - house));
            } else {
                radius = Math.max(radius, Math.min(Math.abs(heaters[j] - house), Math.abs(heaters[j - 1] - house)));
            }
        }
        return radius;
    }
}
