package per.eicho.demo.leetcode_cn.offer;

/**
 * <p>剑指 Offer 04. 二维数组中的查找 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/">
 *   剑指 Offer 04. 二维数组中的查找</a>
 */
public final class Offer04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 1. 0 <= n <= 1000
        // 2. 0 <= m <= 1000
        final int n = matrix.length;
        if (n == 0) return false;
        final int m = matrix[0].length;
        if (m == 0) return false;

        int x = 0, y = m - 1;

        while (isValidCoordinate(x, y, n, m)) {
            final int num = matrix[x][y];
            if (target == num) return true;
            if (target < num) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }

    private boolean isValidCoordinate(int x, int y, int n, int m) {
        if (x < 0 || x >= n) return false;
        if (y < 0 || y >= m) return false;
        return true;
    }

    public static void main(String[] args) {
        final Offer04 offer04 = new Offer04();

        int[][] grid = new int[][]{
            new int[]{1, 4, 7, 11, 15},
            new int[]{2, 5, 8, 12, 19},
            new int[]{3, 6, 9, 16, 22},
            new int[]{10, 13, 14, 17, 24},
            new int[]{18, 21, 23, 26, 30}
        };

        // System.out.println(offer04.findNumberIn2DArray(grid, 20));

        grid = new int[][]{
            new int[]{-1, 3}
        };
        System.out.println(offer04.findNumberIn2DArray(grid, 3));
    }
}
