package per.eicho.demo.leetcode.q601_700;

/**
 * <p>678. Valid Parenthesis String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-parenthesis-string/">
 *   678. Valid Parenthesis String</a>
 */
public final class Q678 {
    public boolean checkValidString(String s) {
        // 1. 1 <= s.length <= 100
        // 2. s[i] is '(', ')' or '*'.        
        final int n = s.length();
        int lv = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);
            if (ch == '(') {
                lv++;
            } else if (ch == ')') {
                lv--;
                if (lv < 0) {
                    cnt--;
                    lv++;
                    if (cnt < 0) return false;
                }
            } else {
                cnt++;
            }
        }

        lv = 0;
        cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            final char ch = s.charAt(i);
            if (ch == ')') {
                lv++;
            } else if (ch == '(') {
                lv--;
                if (lv < 0) {
                    cnt--;
                    lv++;
                    if (cnt < 0) return false;
                }
            } else {
                cnt++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Q678 q678 = new Q678();
        System.out.println(q678.checkValidString("*("));
    }
}
