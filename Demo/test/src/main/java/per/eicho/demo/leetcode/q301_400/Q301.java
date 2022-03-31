package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>301. Remove Invalid Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-invalid-parentheses/">
 *   301. Remove Invalid Parentheses</a>
 */
public final class Q301 {

    private static final Character LEFT_BRACKET = '(';
    private static final Character RIGHT_BRACKET = ')';
    
    private Set<String> resultSet;

    public List<String> removeInvalidParentheses(String s) {
        // 1. 1 <= s.length <= 25
        // 2. s consists of lowercase English letters and parentheses '(' and ')'.
        // 3. There will be at most 20 parentheses in s.        
        final Deque<Character> deleteQueue = genDeleteQueue(s);
        resultSet = new HashSet<>();
        final StringBuilder sb = new StringBuilder(s.length() - deleteQueue.size());
        search(s, 0, deleteQueue, sb, 0);
        return new ArrayList<>(resultSet);
    }

    private void search(String s, int i, Deque<Character> deleteQueue, StringBuilder sb, int lv) {
        if (i == s.length()) {
            if (deleteQueue.size() == 0 && lv == 0) {
                resultSet.add(sb.toString());
            }
            return;
        }

        if (lv < 0) return;

        final char ch = s.charAt(i);
        
        // Case1. no element to delete.
        if (deleteQueue.size() == 0 || deleteQueue.peekFirst().charValue() != ch) {
            sb.append(ch);
            search(s, i + 1, deleteQueue, sb, lv + lvOffset(ch));
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        // Case2. element can delete.

        // Case2.1. can delete
        final Character deletedChar = deleteQueue.pollFirst();
        search(s, i + 1, deleteQueue, sb, lv);
        deleteQueue.addFirst(deletedChar);

        // Case2.2. not delete.
        sb.append(ch);
        search(s, i + 1, deleteQueue, sb, lv + lvOffset(ch));
        sb.deleteCharAt(sb.length() - 1);
    }

    private int lvOffset(char ch) {
        if (ch == '(') return 1;
        if (ch == ')') return -1;
        return 0;
    }

    private Deque<Character> genDeleteQueue(String s) {
        final Deque<Character> deleteQueue = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            if (ch == '(') {
                deleteQueue.add(LEFT_BRACKET);
            } else if (ch == ')') {
                
                if (!deleteQueue.isEmpty() && deleteQueue.peekLast().equals(LEFT_BRACKET)) {
                    deleteQueue.pollLast();
                } else {
                    deleteQueue.add(RIGHT_BRACKET);
                }
            }
        }
        return deleteQueue;
    }

    public static void main(String[] args) {
        Q301 q301 = new Q301();
        // Deque<Character> deque = q301.genDeleteQueue("(a)())()");
        // while (!deque.isEmpty()) {
        //     System.out.println(deque.pollFirst());
        // }
        OutputUtils.println(q301.removeInvalidParentheses("(a)())()"));
    }
}
