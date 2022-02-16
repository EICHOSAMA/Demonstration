package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>917. Reverse Only Letters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-only-letters/">917. Reverse Only Letters</a>
 */
public final class Q917 {
    public String reverseOnlyLetters(String s) {
        // 1. 1 <= s.length <= 100
        // 2. s consists of characters with ASCII values in the range [33, 122].
        // 3. s does not contain '\"' or '\\'.        
        final StringBuilder sb = new StringBuilder(s);
        int l = 0; int r = s.length() - 1;

        while (l < r) {
            while (l < r && !isLetter(s.charAt(l))) l++;
            while (r >= 0 && !isLetter(s.charAt(r))) r--;

            if (l < r) {
                final char ch = s.charAt(l);
                sb.setCharAt(l, s.charAt(r));
                sb.setCharAt(r, ch);
            }

            l++; r--;
        }

        return sb.toString();
    }

    private boolean isLetter(int ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }
}
