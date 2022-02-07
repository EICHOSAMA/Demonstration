package per.eicho.demo.leetcode.q301_400;

/**
 * <p>389. Find the Difference 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-the-difference/">389. Find the Difference</a>
 */
public final class Q389 {
    public char findTheDifference(String s, String t) {
        char result = 0;
        for(int i = 0; i < s.length(); i++) {
            result ^= s.charAt(i);
        }
        for(int i = 0; i < t.length(); i++) {
            result ^= t.charAt(i);
        }
        return result;
    }
}
