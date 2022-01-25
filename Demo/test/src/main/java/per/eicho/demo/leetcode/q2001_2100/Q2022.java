package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2022. Convert 1D Array Into 2D Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/convert-1d-array-into-2d-array/">
 *   2022. Convert 1D Array Into 2D Array</a>
 */
public final class Q2022 {
    public int[][] construct2DArray(int[] original, int m, int n) {
        // 1. 1 <= original.length <= 5 * 10^4
        // 2. 1 <= original[i] <= 10^5
        // 3. 1 <= m, n <= 4 * 10^4
        if (original.length != m * n) return new int[0][0];
        final int[][] result = new int[m][n];

        int row = 0;
        int col = 0;
        for (int i = 0; i < original.length; i++) {
            final int num = original[i];

            result[row][col] = num;
            
            col++;
            if (col == n) {
                col = 0;
                row++;
            }
        }

        return result;
    }
    
    public static void main(String[] args) {
        Q2022 q2022 = new Q2022();
        q2022.construct2DArray(new int[]{1,2,3,4}, 1, 4);
    }
}
