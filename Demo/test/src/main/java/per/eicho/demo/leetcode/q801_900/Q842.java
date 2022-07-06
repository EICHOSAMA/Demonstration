package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>842. Split Array into Fibonacci Sequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/split-array-into-fibonacci-sequence/">
 *  842. Split Array into Fibonacci Sequence</a>
 */
public final class Q842 {
    public List<Integer> splitIntoFibonacci(String num) {
        // 1. 1 <= num.length <= 200
        // 2. num contains only digits.
        final int n = num.length();
        final LinkedList<Integer> workspace = new LinkedList<>();
        long num1 = 0;
        for (int i = 0; i < n; i++) {
            final int digit = num.charAt(i) - '0';
            num1 = num1 * 10L + digit;
            if (num1 > Integer.MAX_VALUE || (i > 0 && num.charAt(0) == '0')) break;
            workspace.add((int)num1);

            long num2 = 0;
            for (int j = i + 1; j < n; j++) {
                final int digit2 = num.charAt(j) - '0';
                num2 = num2 * 10L + digit2;
                if (num2 > Integer.MAX_VALUE || (j > i + 1 && num.charAt(i + 1) == '0')) break;
                workspace.add((int)num2);
                if (search(num, j + 1, workspace) == true) return workspace;
                workspace.pollLast();
            }
            workspace.pollLast();
        }

        return new ArrayList<>();
    }

    private boolean search(String num, int p, LinkedList<Integer> workspace) {
        if (p == num.length() && workspace.size() >= 3) return true;
        final int last = workspace.pollLast();
        final long target = workspace.peekLast() + last;
        workspace.add(last);
        if (target > Integer.MAX_VALUE) return false;
        final LinkedList<Integer> digits = new LinkedList<>();
        
        if (target > 0) {
            int temp = (int)target;

            while (temp > 0) {
                digits.add(temp % 10);
                temp /= 10;
            }
        } else {
            digits.add(0);
        }
        

        while (p < num.length() && digits.size() > 0) {
            if (num.charAt(p++) - '0' != digits.pollLast()) return false;
        }
        if (digits.size() > 0) return false;

        workspace.add((int)target);
        if (search(num, p, workspace)) return true;
        workspace.pollLast();
        return false;
    }

    public static void main(String[] args) {
        Q842 q842 = new Q842();
        List<Integer> result = q842.splitIntoFibonacci("95429520403331817310");
        for (Integer num : result) {
            System.out.println(num);
        }
    }
}
