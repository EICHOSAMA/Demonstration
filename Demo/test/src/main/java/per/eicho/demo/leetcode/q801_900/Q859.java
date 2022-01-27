package per.eicho.demo.leetcode.q801_900;

/**
 * <p>859. Buddy Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/buddy-strings/">859. Buddy Strings</a>
 */
public final class Q859 {
    public boolean buddyStrings(String s, String goal) {
        // 1. 1 <= s.length, goal.length <= 2 * 10^4
        // 2. s and goal consist of lowercase letters.
        if (s.length() != goal.length()) return false;
        if (s.length() == 1) return false;

        char[] pair = new char[2];
        int p = 0;
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            if (s.charAt(i) == goal.charAt(i)) continue;
            if (p == 0) {
                pair[0] = s.charAt(i);
                pair[1] = goal.charAt(i);
                p = 1;
                continue;
            }

            p++;
            if (p > 2) return false;
            if (pair[1] != s.charAt(i) || pair[0] != goal.charAt(i)) return false;
        }

        if (p == 1) return false;
        if (p == 2) return true;

        for (int i = 0; i < 26; i++) {
            if (count[i] >= 2) return true;
        }
        return false;
    }
}
