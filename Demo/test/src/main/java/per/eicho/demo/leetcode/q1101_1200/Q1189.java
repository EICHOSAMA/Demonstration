package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1189. Maximum Number of Balloons 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-number-of-balloons/">
 *   1189. Maximum Number of Balloons</a>
 */
public final class Q1189 {
    public int maxNumberOfBalloons(String text) {
        // 1. 1 <= text.length <= 10^4
        // 2. text consists of lower case English letters only.        
        final int[] counts = new int[26];

        for (int i = 0; i < text.length(); i++) {
            final char ch = text.charAt(i);

            if (ch == 'l' || ch == 'o') {
                counts[ch - 'a']++;
            } else {
                counts[ch - 'a'] += 2;
            }
        }

        int result = Math.min(counts['b' - 'a'], counts['a' - 'a']);
        result = Math.min(result, counts['l' - 'a']);
        result = Math.min(result, counts['o' - 'a']);
        result = Math.min(result, counts['n' - 'a']);
        return result;
    }
}
