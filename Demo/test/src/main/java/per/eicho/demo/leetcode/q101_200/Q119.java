package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>119. Pascal's Triangle II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/pascals-triangle-ii/">119. Pascal's Triangle II</a>
 */
public final class Q119 {
    public List<Integer> getRow(int rowIndex) {
        // 1. 0 <= rowIndex <= 33
        final int[] result = new int[rowIndex + 1];

        int processRowIndex = 0; // start at 0.
        while (processRowIndex <= rowIndex) {
            // add 1 to tail.
            result[processRowIndex] = 1;

            /**
             * row range [0, processRowIndex]
             * update processing range [1, processRowIndex - 1]
             *   process: result[i] += result[i-1];
             */
            for (int i = processRowIndex - 1; i >= 1; i--) {
                result[i] += result[i-1];
            }
            processRowIndex++; // process next row.
        }
        return convertToList(result);
    }

    private List<Integer> convertToList(final int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        for (int num: nums) result.add(num);
        return result;
    }
}
