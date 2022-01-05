package per.eicho.demo.leetcode.q701_800;

import java.text.BreakIterator;

/**
 * <p>796. Rotate String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rotate-string/">796. Rotate String</a>
 */
public final class Q796 {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;

        final int len = s.length();
        for (int offSet = 0; offSet < len; offSet++) {
            
            int i;
            for (i = 0; i < len; i++) {
                if (s.charAt(i) != goal.charAt((i + offSet) % len)) break;
            }
            if (i == len) return true;
        }
        return false;
    }
}
