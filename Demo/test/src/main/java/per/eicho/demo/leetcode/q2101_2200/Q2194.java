package per.eicho.demo.leetcode.q2101_2200;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>2194. Cells in a Range on an Excel Sheet 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/cells-in-a-range-on-an-excel-sheet/">
 *   2194. Cells in a Range on an Excel Sheet</a>
 */
public final class Q2194 {
    public List<String> cellsInRange(String s) {
        // 1. s.length == 5
        // 2. 'A' <= s[0] <= s[3] <= 'Z'
        // 3. '1' <= s[1] <= s[4] <= '9'
        // 4. s consists of uppercase English letters, digits and ':'.
        // i.e. "A1:F1" 
        final char cL = s.charAt(0);
        final char cR = s.charAt(3);
        final int rL = s.charAt(1) - '0';
        final int rR = s.charAt(4) - '0';
        final List<String> result = new ArrayList<>((int)(cR - cL) * (rR - rL));
        for (char c = cL; c <= cR; c++) {
            for (int r = rL; r <= rR; r++) {
                result.add("" + c + r);
            }
        }
        return result;
    }
}
