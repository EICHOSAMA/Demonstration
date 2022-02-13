package per.eicho.demo.leetcode.q001_100;

/**
 * <p>48. Rotate Image 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rotate-image/">48. Rotate Image</a>
 */
public final class Q48 {
    public void rotate(int[][] matrix) {
        // 1. n == matrix.length == matrix[i].length
        // 2. 1 <= n <= 20
        // 3. -1000 <= matrix[i][j] <= 1000        
        for (int start = 0, len = matrix.length, mid = len / 2; start < mid; start++) {
            // process range [l, r]
            final int l = start;
            final int r = len - 1 - l;
            final int newLen = r - l + 1;

            // [l, r)
            for (int offset = 0; offset < newLen - 1; offset++) {
                rotate(matrix, l, r, offset);
            }
        }
    }

    private void rotate(final int[][] matrix,
                        final int l,
                        final int r,
                        final int offset) {
        // l,l
        // 1. l, l + offset
        // 2. l + offset, r
        // 3. r, r - offset
        // 4. r - offset, l

        int temp = matrix[l][l + offset]; // temp = 1
        matrix[l][l + offset] = matrix[r - offset][l]; // 1 = 4
        matrix[r - offset][l] = matrix[r][r - offset]; // 4 = 3
        matrix[r][r - offset] = matrix[l + offset][r]; // 3 = 2
        matrix[l + offset][r] = temp; // 2 = temp
    }
}
