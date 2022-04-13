package per.eicho.demo.leetcode.q301_400;

/**
 * <p>378. Kth Smallest Element in a Sorted Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/">
 *   378. Kth Smallest Element in a Sorted Matrix</a>
 */
public final class Q378 {
    public int kthSmallest(int[][] matrix, int k) {
        // 1. n == matrix.length == matrix[i].length
        // 2. 1 <= n <= 300
        // 3. -10^9 <= matrix[i][j] <= 10^9
        // 4. All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
        // 5. 1 <= k <= n^2
        final int n = matrix.length;
        
        int l = matrix[0][0], r = matrix[n - 1][n - 1];
        while (l < r) {
            final int mid = l + ((r - l) >> 1);
            if (greaterThanK(matrix, mid, k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public boolean greaterThanK(int[][] matrix, int target, int k) {
        final int n = matrix.length;

        int i = n - 1, j = 0; // BOTTOM, LEFT
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= target) {
                count += i + 1;
                j++; // →
            } else {
                i--; // ↑
            }
        }
        return count >= k;
    }

    public static void main(String[] args) {
        Q378 q378 = new Q378();
        System.out.println(q378.kthSmallest(new int[][]{new int[]{1,5,9}, new int[]{10,11,13}, new int[]{12,13,15}}, 8));
    }
}
