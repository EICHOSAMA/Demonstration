package per.eicho.demo.leetcode.q401_500;

import java.util.PriorityQueue;

import per.eicho.demo.leetcode.debug.InputUtils;

/**
 * <p>407. Trapping Rain Water II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/trapping-rain-water-ii/">
 *   407. Trapping Rain Water II</a>
 */
public final class Q407 {

    private static final int[][] directions = new int[][]{
        new int[]{1, 0}, new int[]{-1, 0},
        new int[]{0, 1}, new int[]{0, -1}
    };

    private static final int X = 0;
    private static final int Y = 1;
    private static final int H = 2;

    public int trapRainWater(int[][] heightMap) {
        // 1. m == heightMap.length
        // 2. n == heightMap[i].length
        // 3. 1 <= m, n <= 200
        // 4. 0 <= heightMap[i][j] <= 2 * 10^4
        final int m = heightMap.length;
        final int n = heightMap[0].length;
        final int total = m * n;
        final PriorityQueue<int[]> minHeap = 
            new PriorityQueue<>((i1, i2) -> i1[H] - i2[H]);
        final boolean[][] marks = new boolean[m][n];

        int count = 0;
        for (int i = 0, bot = m - 1; i < n; i++) {
            add(heightMap, minHeap, marks, 0, i);
            add(heightMap, minHeap, marks, bot, i);
            count += 2;
        }

        for (int i = 1, r = n - 1, bound = m - 2; i <= bound; i++) {
            add(heightMap, minHeap, marks, i, 0);
            add(heightMap, minHeap, marks, i, r);
            count += 2;
        }

        int result = 0;
        while (count < total) {
            final int[] info = minHeap.poll();

            for (int[] direction : directions) {
                final int nx = info[X] + direction[X];
                if (nx < 0 || nx >= m) continue;
                final int ny = info[Y] + direction[Y];
                if (ny < 0 || ny >= n) continue;
                if (marks[nx][ny]) continue;

                if (heightMap[nx][ny] < info[H]) {
                    result += (info[H] - heightMap[nx][ny]);
                    heightMap[nx][ny] = info[H];
                }
                
                add(heightMap, minHeap, marks, nx, ny);
                count++;
            }
        }

        return result;
    }

    private void add(int[][] heightMap, final PriorityQueue<int[]> minHeap, final boolean[][] marks, int x,
            int y) {
        minHeap.offer(new int[]{x, y, heightMap[x][y]});
        marks[x][y] = true;
    }

    public static void main(String[] args) {
        Q407 q407 = new Q407();
        System.out.println(q407.trapRainWater(InputUtils.gen2DArray("[[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]")));
    }
}
