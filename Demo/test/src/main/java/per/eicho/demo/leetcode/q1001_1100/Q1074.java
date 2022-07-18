package per.eicho.demo.leetcode.q1001_1100;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>1074. Number of Submatrices That Sum to Target 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/">
 *   1074. Number of Submatrices That Sum to Target</a>
 */
public final class Q1074 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // 1. 1 <= matrix.length <= 100
        // 2. 1 <= matrix[0].length <= 100
        // 3. -1000 <= matrix[i] <= 1000
        // 4. -10^8 <= target <= 10^8
        final int m = matrix.length;
        final int n = matrix[0].length;
        int result = 0;
        for (int top = 0; top < m; top++) {
            final int[] sums = new int[n];
            for (int bot = top; bot < m; bot++) {
                final int[] row = matrix[bot];
                for (int y = 0; y < n; y++) sums[y] += row[y];
                result += count(sums, target);
            }
        }

        return result;
    }

    private int count(int[] nums, int target) {
        final int n = nums.length;
        final Map<Integer, int[]> map = new HashMap<>();
        int count = 0, sum = 0;
        map.put(0, new int[]{1});
        for (int i = 0; i < n; i++) {
            final int num = nums[i];
            sum += num;

            if (map.containsKey(sum - target)) count += map.get(sum - target)[0];
            if (!map.containsKey(sum)) map.put(sum, new int[]{0});
            map.get(sum)[0]++;
        }

        return count;
    }

    public static void main(String[] args) {
        Q1074 q1074 = new Q1074();
        System.out.println(q1074.numSubmatrixSumTarget(new int[][]{
            new int[]{0,1,0},
            new int[]{1,1,1},
            new int[]{0,1,0}
        }, 0));
    }
}
