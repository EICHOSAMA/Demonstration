package per.eicho.demo.leetcode.q801_900;

/**
 * <p>806. Number of Lines To Write String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-lines-to-write-string/">806. Number of Lines To Write String</a>
 */
public final class Q806 {

    static final int ROW_WIDTH_LIMIT = 100;

    public int[] numberOfLines(int[] widths, String s) {
        // 1. widths.length == 26
        // 2. 2 <= widths[i] <= 10
        // 3. 1 <= s.length <= 1000
        // 4. s contains only lowercase English letters.
        int rowCount = 1;
        int widthOfLastRow = 0;        

        for (int i = 0; i < s.length(); i++) {
            final int ch = s.charAt(i);
            final int width = widths[ch - 'a'];

            if (widthOfLastRow + width > ROW_WIDTH_LIMIT) {
                rowCount++;
                widthOfLastRow = width;
            } else {
                widthOfLastRow += width;
            }
        }

        return new int[]{rowCount, widthOfLastRow};
    }
}
