package per.eicho.demo.leetcode.q1101_1200;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>1128. Number of Equivalent Domino Pairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-equivalent-domino-pairs/">1128. Number of Equivalent Domino Pairs</a>
 */
public final class Q1128 {
    public int numEquivDominoPairs(int[][] dominoes) {
        // 1. 1 <= dominoes.length <= 4 * 10^4
        // 2. dominoes[i].length == 2
        // 3. 1 <= dominoes[i][j] <= 9

        final Map<String, Integer> map = new HashMap<>();

        int result = 0;
        for (int[] domino : dominoes) {
            if (domino[0] > domino[1]) {
                domino[0] ^= domino[1];
                domino[1] ^= domino[0];
                domino[0] ^= domino[1];
            }

            final String key = "" + domino[0] + domino[1];
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                result += map.put(key, map.get(key) + 1);
            }
        }

        return result;
    }
}
