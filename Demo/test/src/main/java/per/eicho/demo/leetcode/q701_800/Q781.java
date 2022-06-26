package per.eicho.demo.leetcode.q701_800;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>781. Rabbits in Forest 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rabbits-in-forest/">
 *   781. Rabbits in Forest</a>
 */
public final class Q781 {
    public int numRabbits(int[] answers) {
        // 1. 1 <= answers.length <= 1000
        // 2. 0 <= answers[i] < 1000
        final Map<Integer, int[]> map = new HashMap<>();
        for (int answer : answers) {
            if (!map.containsKey(answer)) map.put(answer, new int[]{0});
            map.get(answer)[0]++;
        }

        int result = 0;
        for (int answer : map.keySet()) {
            final int count = map.get(answer)[0];
            answer++;
            result += (count / answer + (count % answer > 0 ? 1 : 0)) * answer;
        }

        return result;
    }
}
