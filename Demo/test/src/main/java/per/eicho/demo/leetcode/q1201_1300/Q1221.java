package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1221. Split a String in Balanced Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/split-a-string-in-balanced-strings/">
 *   1221. Split a String in Balanced Strings</a>
 */
public final class Q1221 {
    public int balancedStringSplit(String s) {
        // 1. 1 <= s.length <= 1000
        // 2. s[i] is either 'L' or 'R'.
        // 3. s is a balanced string.        
        int result = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);

            if (ch == 'L') {
                count++;
            } else {
                count--;
            }

            if (count == 0) result++;
        }
        return result;
    }
}
