package per.eicho.demo.leetcode.q101_200;

/**
 * <p>171. Excel Sheet Column Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/excel-sheet-column-number/">171. Excel Sheet Column Number</a>
 */
public final class Q171 {
    public int titleToNumber(String s) {
        // 1. 1 <= columnTitle.length <= 7
        // 2. columnTitle consists only of uppercase English letters.
        // 3. columnTitle is in the range ["A", "FXSHRXW"].        
        int result = 0;
        int base = 1;
        for (int i = s.length() - 1; i >= 0 ; i--, base *= 26) {
            result +=  base * (int)(s.charAt(i) - '@');
        }
        return result;
    }
}
