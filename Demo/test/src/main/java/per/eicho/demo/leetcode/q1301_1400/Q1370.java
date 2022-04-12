package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1370. Increasing Decreasing String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/increasing-decreasing-string/">
 *   1370. Increasing Decreasing String</a>
 */
public final class Q1370 {
    public String sortString(String s) {
        // 1. 1 <= s.length <= 500
        // 2. s consists of only lowercase English letters.        
        final int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) counts[s.charAt(i) - 'a']++;

        final StringBuilder sb = new StringBuilder(s.length());
        int remain = s.length();
        int direction = 1;
        int p = -1;
        while (remain != 0) {

            p += direction;
            if (p == 26) {
                direction = -1;
            } else if (p == -1) {
                direction = 1;
            } else {
                if (counts[p] == 0) continue;
                
                remain--;
                counts[p]--;
                sb.append((char)(p + 'a'));
            }
        }
        return sb.toString();
    }
}
