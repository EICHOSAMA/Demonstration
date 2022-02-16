package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>925. Long Pressed Name 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/long-pressed-name/">925. Long Pressed Name</a>
 */
public final class Q925 {
    public boolean isLongPressedName(String name, String typed) {
        // 1. 1 <= name.length, typed.length <= 1000
        // 2. name and typed consist of only lowercase English letters.        
        final List<Character> characters = new LinkedList<>();
        final List<Integer> counts = new LinkedList<>();

        int p = 0;
        while (p < name.length()) {
            final char ch = name.charAt(p);
            int count = 1;

            while (p + 1 < name.length() && name.charAt(p + 1) == ch) {
                p++;
                count++;
            }

            characters.add(ch);
            counts.add(count);
            p++;
        }

        p = 0;
        int c = 0;
        while (p < typed.length()) {
            final char ch = typed.charAt(p);
            int count = 1;

            while (p + 1 < typed.length() && typed.charAt(p + 1) == ch) {
                p++;
                count++;
            }

            if (c >= characters.size()) return false;
            if (characters.get(c) != ch) return false;
            if (count < counts.get(c)) return false;
            c++;
            p++;
        }        

        return c == characters.size();
    }
}
