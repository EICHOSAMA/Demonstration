package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>406. Queue Reconstruction by Height 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/queue-reconstruction-by-height/">
 *   406. Queue Reconstruction by Height</a>
 */
public final class Q406 {
    public int[][] reconstructQueue(int[][] people) {
        // 1. 1 <= people.length <= 2000
        // 2. 0 <= hi <= 10^6
        // 3. 0 <= ki < people.length
        // 4. It is guaranteed that the queue can be reconstructed.        
        Arrays.sort(people, (p1, p2) -> {
            if (p1[0] != p2[0]) return Integer.compare(p1[0], p2[0]);
            return Integer.compare(p1[1], p2[1]);
        });

        final int n = people.length;
        final int[][] result = new int[n][2];
        for (int i = 0; i < n; i++) { result[i][0] = -1; }

        for (int i = 0; i < n; i++) {
            final int[] p = people[i];
            final int height = p[0];
            
            int cnt = 0;
            int idx = 0;

            while (i < n && people[i][0] == height) {
                final int k = people[i][1];

                while (cnt != k) {
                    if (result[idx][0] == -1) {
                        idx++;
                        cnt++;
                    } else {
                        idx++;
                    }
                }

                while (result[idx][0] != -1) idx++;

                result[idx][0] = people[i][0];
                result[idx][1] = people[i][1];
                cnt++;
                i++;
            }
            i--;

        }

        return result;
    }

    public static void main(String[] args) {
        Q406 q406 = new Q406();
        
        int[][] result = q406.reconstructQueue(new int[][]{new int[]{7, 0}, new int[]{4, 4}, new int[]{7, 1}, new int[]{5, 0}, new int[]{6, 1}, new int[]{5, 2},});
        for (int[] pair : result) {
            System.out.println(pair[0] + ":" + pair[1]);
        }
    }
}
