package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1576. Replace All ?'s to Avoid Consecutive Repeating Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/">
 *   1576. Replace All ?'s to Avoid Consecutive Repeating Characters</a>
 */
public final class Q1576 {
    public String modifyString(String s) {
        // 1. 1 <= s.length <= 100
        // 2. s consist of lowercase English letters and '?'.
        final StringBuilder sb = new StringBuilder(s);
        final int n = sb.length();
        final boolean[] mark = new boolean[26]; // ['a' - 'z']
        final int offset = 'a';
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);
            if (i + 1 < n && sb.charAt(i + 1) != '?') mark[s.charAt(i + 1) - 'a'] = true;

            if (ch == '?') {
                int idx = 0;
                while (mark[idx] == true) idx++;

                sb.setCharAt(i, (char)(idx + offset));
                mark[idx] = true; // mark
            } else {
                mark[ch - 'a'] = true;
            }

            if (i - 1 >= 0) mark[sb.charAt(i - 1) - 'a'] = false;
        }
        return sb.toString();
    }
}
