package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>118. Pascal's Triangle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/pascals-triangle/">118. Pascal's Triangle</a>
 */
public final class Q118 {
    public List<List<Integer>> generate(int numRows) {
        // 1. 1 <= numRows <= 30
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows < 0) return result;

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i+1);
            row.add(1);
            for (int j = 1; j < i ; j++) {
                row.add(result.get(i-1).get(j - 1) + result.get(i-1).get(j));
            }
            if (i != 0) row.add(1);

            result.add(row);
        }

        return result;
    }
}
