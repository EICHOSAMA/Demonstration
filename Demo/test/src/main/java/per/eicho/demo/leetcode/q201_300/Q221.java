package per.eicho.demo.leetcode.q201_300;

/**
 * <p>221. Maximal Square 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximal-square/">221. Maximal Square</a>
 */
public final class Q221 {
    public int maximalSquare(char[][] matrix) {
        // 1. m == matrix.length
        // 2. n == matrix[i].length
        // 3. 1 <= m, n <= 300
        // 4. matrix[i][j] is '0' or '1'.
        final int m = matrix.length;
        final int n = matrix[0].length;
        final int[][] sums = new int[m][n];
     
        // 1. calculate sum.
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') sum++;
            sums[0][i] = sum;
        }

        for (int i = 1; i < m; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') sum++;
                sums[i][j] = sum + sums[i - 1][j];
            }
        }

        if (sums[m - 1][n - 1] == 0) return 0;
        int result = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '0') continue;

                int maxSize = Math.min(i + 1, j + 1);
                int minSize = result;

                if (isSquare(sums, i, j, minSize) == false) continue;

                while (minSize < maxSize) {
                    int midSize = (maxSize + minSize + 1) / 2;

                    if (isSquare(sums, i, j, midSize)) {
                        minSize = midSize;
                    } else {
                        maxSize = midSize - 1;
                    }
                }

                result = minSize;
            }
        }

        System.out.println(isSquare(sums, 2, 4, 2));

        return result * result;
    }

    private boolean isSquare(int[][] sums, int x, int y, int size) {
        final int x1 = x - size;
        final int y1 = y - size;
        
        final int a = sums[x][y];
        final int b = x1 >= 0 && y1 >= 0 ? sums[x1][y1] : 0;
        final int c = y1 >= 0 ? sums[x][y1] : 0;
        final int d = x1 >= 0 ? sums[x1][y] : 0;

        return a + b - c - d == size * size;
    }
}
