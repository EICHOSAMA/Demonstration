package per.eicho.demo.leetcode.q2001_2100;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>2053. Kth Distinct String in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-distinct-string-in-an-array/">
 *   2053. Kth Distinct String in an Array</a>
 */
public final class Q2053 {
    public String kthDistinct(String[] arr, int k) {
        // 1. 1 <= k <= arr.length <= 1000
        // 2. 1 <= arr[i].length <= 5
        // 3. arr[i] consists of lowercase English letters.
        final Map<String, Integer> map = new HashMap<>();

        for (String token : arr) {
            if (!map.containsKey(token)) {
                map.put(token, 1);
            } else {
                map.put(token, map.get(token) + 1);
            }
        }

        int rank = 0;
        for (String token : arr) {
            if (map.get(token) > 1) continue;
            rank++;
            if (rank == k) return token;
        }

        return "";
    }
}
