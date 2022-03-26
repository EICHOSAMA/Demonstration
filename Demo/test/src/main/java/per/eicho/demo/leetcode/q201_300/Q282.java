package per.eicho.demo.leetcode.q201_300;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>282. Expression Add Operators 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/expression-add-operators/">
 *   282. Expression Add Operators</a>
 */
public class Q282 {
    int n;
    String num;
    int target;
    List<String> ans;

    public List<String> addOperators(String num, int target) {
        // 1. 1 <= num.length <= 10
        // 2. num consists of only digits.
        // 3. -2^31 <= target <= 2^31 - 1        
        n = num.length();
        this.num = num;
        this.target = target;
        this.ans = new LinkedList<String>();
        final StringBuilder sb = new StringBuilder();
        search(sb, 0, 0, 0);
        return ans;
    }

    public void search(StringBuilder sb, int p, long res, long mul) {
        if (p == n) {
            if (res == target) ans.add(sb.toString());
            return;
        }
        
        int opIndex = sb.length();
        if (p > 0) sb.append('s'); // symbol byte. (For replacement setCharAt(opIndex, op))
        long val = 0;

        final boolean leadingZero = num.charAt(p) == '0';

        // Note that operands in the returned expressions should not contain leading zeros.
        for (int i = p; i < n && (i == p || !leadingZero); i++) {
            val = val * 10L + (num.charAt(i) - '0');
            sb.append(num.charAt(i));
            if (p == 0) {
                // can not add op to the begin of the expression
                search(sb, i + 1, val, val);
                continue;
            }
            
            // +
            sb.setCharAt(opIndex, '+');
            search(sb, i + 1, res + val, val);

            // -
            sb.setCharAt(opIndex, '-');
            search(sb, i + 1, res - val, -val);

            // *
            sb.setCharAt(opIndex, '*');
            search(sb, i + 1, (res - mul) + (mul * val), (mul * val));
        
        }
        sb.setLength(opIndex);
    }
}
