package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1446. Consecutive Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/consecutive-characters/">
 *   1446. Consecutive Characters</a>
 */
public final class Q1446 {
    public int maxPower(String s) {
        // 1. 1 <= s.length <= 500
        // 2. s consists of only lowercase English letters.        
        final int n = s.length();
        int result = 0;
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);

            int j = i + 1;
            while (j < n && s.charAt(j) == ch) j++;
            
            result = Math.max(result, j - i);
            i = j - 1;
        }

        return result;
    }
}
