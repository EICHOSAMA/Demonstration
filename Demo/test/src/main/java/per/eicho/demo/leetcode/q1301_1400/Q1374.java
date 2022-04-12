package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1374. Generate a String With Characters That Have Odd Counts 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/">
 *   1374. Generate a String With Characters That Have Odd Counts</a>
 */
public final class Q1374 {
    public String generateTheString(int n) {
        // 1. 1 <= n <= 500
        final StringBuilder sb = new StringBuilder(n);

        if (n % 2 == 1) {
            for (int i = 0; i < n; i++) sb.append('a');
        } else {
            n--;
            for (int i = 0; i < n; i++) sb.append('a');
            sb.append('b');
        }
        return sb.toString();
    }
}
