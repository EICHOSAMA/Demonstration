package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2027. Minimum Moves to Convert String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-moves-to-convert-string/">
 *   2027. Minimum Moves to Convert String</a>
 */
public final class Q2027 {
    public int minimumMoves(String s) {
        
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'O') continue;
            i += 2;
        }

        return result;
    }
}
