package per.eicho.demo.leetcode.q1001_1100;

import java.util.LinkedList;

/**
 * <p>1003. Check If Word Is Valid After Substitutions 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/">
 *   1003. Check If Word Is Valid After Substitutions</a>
 */
public final class Q1003 {
    public boolean isValid(String s) {
        // 1. 1 <= s.length <= 2 * 10^4
        // 2. s consists of letters 'a', 'b', and 'c'
        final int n = s.length();
        final LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            final Character ch = s.charAt(i);
            list.addLast(ch);

            while (list.size() >= 3) {
                int last = list.size() - 1;
                if (list.get(last--) != 'c') break;
                if (list.get(last--) != 'b') break;
                if (list.get(last--) != 'a') break;
                list.pollLast();
                list.pollLast();
                list.pollLast();
            }
        } 

        return list.isEmpty();
    }
}
