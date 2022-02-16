package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>942. DI String Match 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/di-string-match/">942. DI String Match</a>
 */
public final class Q942 {
    public int[] diStringMatch(String s) {
        // 1 <= s.length <= 10^5
        // s[i] is either 'I' or 'D'.        
        final int n = s.length();
        final int[] result = new int[n + 1];

        int min = 0, max = n;
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);

            if (ch == 'I') {
                result[i] = min++;
            } else {
                result[i] = max--; 
            }
        }
        result[n] = min;
        return result;
    }
}
