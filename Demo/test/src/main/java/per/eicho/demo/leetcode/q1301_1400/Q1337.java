package per.eicho.demo.leetcode.q1301_1400;

import java.util.PriorityQueue;

/**
 * <p>1337. The K Weakest Rows in a Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/">
 *   1337. The K Weakest Rows in a Matrix</a>
 */
public final class Q1337 {
    public int[] kWeakestRows(int[][] mat, int k) {
        // 1. m == mat.length
        // 2. n == mat[i].length
        // 3. 2 <= n, m <= 100
        // 4. 1 <= k <= m
        // 5. matrix[i][j] is either 0 or 1.        
        final int m = mat.length;
        final int n = mat[0].length;

        final int[][] countInfo = new int[m][2];
        for (int i = 0; i < m; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    count++;
                } else {
                    break;
                }
            }

            countInfo[i][0] = i;
            countInfo[i][1] = count;
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((info1, info2) -> {
            if (info1[1] != info2[1]) return Integer.compare(info1[1], info2[1]);
            return Integer.compare(info1[0], info2[0]);
        });

        for (int i = 0; i < m; i++) {
            minHeap.add(countInfo[i]);
        }

        final int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0];
        }

        return result;
    }
}
