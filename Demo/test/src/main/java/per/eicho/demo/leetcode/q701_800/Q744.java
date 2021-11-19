package per.eicho.demo.leetcode.q701_800;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>744. Find Smallest Letter Greater Than Target 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-smallest-letter-greater-than-target/">744. Find Smallest Letter Greater Than Target</a>
 */
public final class Q744 {
    public char nextGreatestLetter(char[] letters, char target) {
        final Set<Character> set = new HashSet<>();

        for (char ch : letters) {
            set.add(ch);
        }

        while (true) {
            target++;
            if (target > 'z') target = 'a';

            if (set.contains(target)) return target;
        }
    }
}
