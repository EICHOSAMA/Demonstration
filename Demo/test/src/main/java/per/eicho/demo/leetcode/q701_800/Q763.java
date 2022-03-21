package per.eicho.demo.leetcode.q701_800;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <p>763. Partition Labels 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partition-labels/">
 *   763. Partition Labels</a>
 */
public final class Q763 {
    public List<Integer> partitionLabels(String s) {
        // 1. 1 <= s.length <= 500
        // 2. s consists of lowercase English letters.            
        final int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            counts[ch - 'a']++;
        }

        final List<Integer> result = new LinkedList<>();
        final Set<Character> marks = new HashSet<>();
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            final int idx = ch - 'a';

            if (!marks.contains(ch)) {
                // check.
                boolean newStr = true;
                for (char markedChar : marks) {
                    if (counts[markedChar - 'a'] == 0) continue;
                    newStr = false;
                    break;
                }

                if (newStr) {
                    marks.clear();
                    result.add(len);
                    len = 0;
                } 

                marks.add(ch);
            }

            len++;
            counts[idx]--;
        }
        if (len != 0) result.add(len);
        result.remove(0);
        return result;
    }
}
