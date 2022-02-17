package per.eicho.demo.leetcode.q1001_1100;

import java.util.LinkedList;

/**
 * <p>1047. Remove All Adjacent Duplicates In String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/">
 *   1047. Remove All Adjacent Duplicates In String</a>
 */
public final class Q1047 {
    public String removeDuplicates(String s) {
        final LinkedList<Character> linkedList = new LinkedList<>(); 

        for (int i = 0; i < s.length(); i++) {
            final Character ch = s.charAt(i);

            if (linkedList.isEmpty()) {
                linkedList.add(ch);
                continue;
            } 

            if (linkedList.peekLast().equals(ch)) {
                linkedList.pollLast();
            } else {
                linkedList.add(ch);
            }
        }

        final StringBuilder sb = new StringBuilder(linkedList.size());
        for (char ch : linkedList) {
            sb.append(ch);
        }

        return sb.toString();
    }
}
