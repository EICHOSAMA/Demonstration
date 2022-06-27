package per.eicho.demo.leetcode.q1401_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>1424. Diagonal Traverse II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii/">
 *   1424. Diagonal Traverse II</a>
 */
public final class Q1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 1 <= nums[i].length <= 10^5
        // 3. 1 <= sum(nums[i].length) <= 10^5
        // 4. 1 <= nums[i][j] <= 10^5
        final long n = 100_000;
        final List<Long> indexes = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            final List<Integer> row = nums.get(i);
            for (int j = 0; j < row.size(); j++) indexes.add(n * i + j);
        }

        indexes.sort((idx1, idx2) -> {
            final int i1 = (int)(idx1 / n);
            final int j1 = (int)(idx1 % n);
            final int i2 = (int)(idx2 / n);
            final int j2 = (int)(idx2 % n);

            final int diagonalIdx1 = i1 + j1;
            final int diagonalIdx2 = i2 + j2;
            // ascending
            if (diagonalIdx1 != diagonalIdx2) return Integer.compare(diagonalIdx1, diagonalIdx2);
            return Integer.compare(i2, i1); // descending.
        });

        final int[] result = new int[indexes.size()];
        for (int i = 0; i < result.length; i++) {
            final long idx = indexes.get(i);
            result[i] = nums.get((int)(idx / n)).get((int)(idx % n));
        }

        return result;
    }

    public static void main(String[] args) {
        Q1424 q1424 = new Q1424();
        OutputUtils.println(q1424.findDiagonalOrder(Arrays.asList(Arrays.asList(1,2,3,4,5,6))));
    }
}
