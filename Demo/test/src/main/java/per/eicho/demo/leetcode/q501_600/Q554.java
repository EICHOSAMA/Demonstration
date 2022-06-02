package per.eicho.demo.leetcode.q501_600;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>554. Brick Wall 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/brick-wall/">
 *   554. Brick Wall</a>
 */
public final class Q554 {
    public int leastBricks(List<List<Integer>> wall) {
        // 1. n == wall.length
        // 2. 1 <= n <= 10^4
        // 3. 1 <= wall[i].length <= 10^4
        // 4. 1 <= sum(wall[i].length) <= 2 * 10^4
        // 5. sum(wall[i]) is the same for each row i.
        // 6. 1 <= wall[i][j] <= 2^31 - 1
        final int n = wall.size();
        final Map<Integer, int[]> map = new HashMap<>();

        for (List<Integer> row : wall) {
            int edge = 0;
            final int bound = row.size() - 1;
            for (int i = 0; i < bound; i++) {
                edge += row.get(i);

                if (!map.containsKey(edge)) map.put(edge, new int[]{0});
                map.get(edge)[0]++;
            }
        }

        int result = n;
        for (Integer key : map.keySet()) {
            result = Math.min(result, n - map.get(key)[0]);
        }
        return result;
    }
}
