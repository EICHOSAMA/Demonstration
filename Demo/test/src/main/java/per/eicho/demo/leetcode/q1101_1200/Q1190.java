package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1190. Reverse Substrings Between Each Pair of Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/">
 *   1190. Reverse Substrings Between Each Pair of Parentheses</a>
 */
public final class Q1190 {
    public String reverseParentheses(String s) {
        // 1. 1 <= s.length <= 2000
        // 2. s only contains lower case English characters and parentheses.
        // 3. It is guaranteed that all parentheses are balanced.
        return process(s, 0, s.length() - 1, 0);
    }

    private String process(final String s, final int l, final int r, final int lv) {
        if (l > r) return "";
        
        final StringBuilder sb = new StringBuilder(r - l + 1);
        if (lv % 2 == 1) {
            // R -> L
            for (int i = r; i >= l; i--) {
                char ch = s.charAt(i);
                
                if (ch != ')') {
                    sb.append(ch);
                    continue;
                }

                final int nR = i;
                int nLv = 1;
                while (nLv != 0) {
                    ch = s.charAt(--i);
                    if (ch == '(') {
                        nLv--;
                    } else if (ch == ')') {
                        nLv++;
                    }
                }
    
                final String str = process(s, i + 1, nR - 1, lv + 1);
                for (int j = 0; j < str.length(); j++) sb.append(str.charAt(j));
            }
        } else {
            // L -> R
            for (int i = l; i <= r; i++) {
                char ch = s.charAt(i);

                if (ch != '(') {
                    sb.append(ch);
                    continue;
                }

                final int nL = i;
                int nLv = 1;
                while (nLv != 0) {
                    ch = s.charAt(++i);
                    if (ch == '(') {
                        nLv++;
                    } else if (ch == ')') {
                        nLv--;
                    }
                }

                final String str = process(s, nL + 1, i - 1, lv + 1);
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Q1190 q1190 = new Q1190();
        System.out.println(q1190.reverseParentheses("(u(love)i)"));
    }
}
