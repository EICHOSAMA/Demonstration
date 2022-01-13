package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * <p>131. Palindrome Partitioning 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/palindrome-partitioning/">131. Palindrome Partitioning</a>
 */
public final class Q131 {

    private List<List<String>> result;

    private Stack<Integer> solution;
    private String str;
    private int length;

    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        solution = new Stack<>();
        str = s;
        length = s.length();
        search(0);
        return result;
    }

    private void search(int p) {
        if (p == length) {
            // solution to result;
            List<String> partition = new LinkedList<>();
            int pointer = 0;
            for (int len : solution) {
                partition.add(str.substring(pointer, pointer + len));
                pointer += len;
            }
            result.add(partition);
            return;
        }
        
        for (int len = 1; len <= length - p; len++) {
            if (isPalindrome(p, p + len)) {
                solution.push(len);
                search(p + len);
                solution.pop();
            }
        }
    }

    private boolean isPalindrome(int l, int r) {
        // [l, r)
        r--;
        while (l < r) {
            if (str.charAt(l) != str.charAt(r)) return false;
            l++; r--;
        }
        return true;
    }
}
