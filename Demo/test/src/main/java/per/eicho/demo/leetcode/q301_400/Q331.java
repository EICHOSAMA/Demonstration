package per.eicho.demo.leetcode.q301_400;

import java.util.Arrays;

/**
 * <p>331. Verify Preorder Serialization of a Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/">
 *   331. Verify Preorder Serialization of a Binary Tree</a>
 */
public final class Q331 {
    public boolean isValidSerialization(String preorder) {
        // 1. 1 <= preorder.length <= 10^4
        // 2. preorder consist of integers in the range [0, 100] and '#' separated by commas ','.
        if (preorder.charAt(0) == '#') return preorder.length() == 1;
        final int n = preorder.length();
        int[] stack = new int[20];
        int stackSize = 0;

        int p = 0;
        while (p < n) {
            final char ch = preorder.charAt(p++);
            if (ch == ',') continue;

            if (ch == '#') {
                if (stackSize == 0) return false;

                stack[stackSize - 1]++;
                if (stack[stackSize - 1] == 2) {
                    // pop
                    stack[stackSize - 1] = 0;
                    stackSize--;
                }

                if (stackSize == 0 && p < n) return false;
                continue;
            }

            while (p < n && isDigit(preorder.charAt(p))) p++; // skip digit

            // root node will case out of bounds error.
            if (stackSize != 0) {
                stack[stackSize - 1]++;
                if (stack[stackSize - 1] == 2) {
                    // pop
                    stack[stackSize - 1] = 0;
                    stackSize--;
                }
            }

            if (stackSize >= stack.length) {
                stack = Arrays.copyOf(stack, stack.length * 2);
            }
            stackSize++;
        }

        return stackSize == 0;
    }

    private boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public static void main(String[] args) {
        Q331 q331 = new Q331();
        System.out.println(q331.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(q331.isValidSerialization("#"));
    }
}
