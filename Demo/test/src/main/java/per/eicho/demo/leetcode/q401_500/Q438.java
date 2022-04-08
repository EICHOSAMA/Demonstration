package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>438. Find All Anagrams in a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-all-anagrams-in-a-string/">438. Find All Anagrams in a String</a>
 */
public final class Q438 {
    public List<Integer> findAnagrams(String s, String p) {
        // 1. 1 <= s.length, p.length <= 3 * 10^4
        // 2. s and p consist of lowercase English letters.        
        final List<Integer> result = new ArrayList<>();
        // 1. prepare hash code.
        int hashCode = 0;
        for (int i = 0; i < p.length() ; i++) {
            hashCode += (p.charAt(i) * p.charAt(i) * p.charAt(i));
        }

        final int lenP = p.length();
        int hashCode2 = 0;
        int len = 0;
        for (int i = 0; i < s.length() ; i++) {
            len++;
            hashCode2 += s.charAt(i) * s.charAt(i) * s.charAt(i);
            if (len > lenP) {
                len--;
                hashCode2 -= s.charAt(i - len) * s.charAt(i - len) * s.charAt(i - len);
            }

            if (hashCode == hashCode2) {
                result.add(i - len + 1);
            }
        }

        return result;
    }
}
