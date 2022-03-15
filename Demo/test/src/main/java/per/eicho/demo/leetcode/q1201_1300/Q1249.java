package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1249. Minimum Remove to Make Valid Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/">
 *   1249. Minimum Remove to Make Valid Parentheses</a>
 */
public final class Q1249 {
    public String minRemoveToMakeValid(String s) {
        // 1. 1 <= s.length <= 10^5
        // 2. s[i] is either'(' , ')', or lowercase English letter.        
        final int[] count = new int[2];
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            if (ch == '(') {
                count[0]++;
            } else if (ch == ')') {
                count[1]++;
            }
        }

        final StringBuilder sb = new StringBuilder();
        int lv = 0;
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            if (ch == '(') {
                count[0]--;
                if (count[1] <= lv) continue;
                // count[1] > lv
                lv++;
                sb.append(ch);
            } else if (ch == ')') {
                count[1]--;
                if (lv == 0) continue;
                lv--;
                sb.append(ch);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
