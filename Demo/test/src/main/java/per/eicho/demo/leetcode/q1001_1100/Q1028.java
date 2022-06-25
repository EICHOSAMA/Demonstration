package per.eicho.demo.leetcode.q1001_1100;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1028. Recover a Tree From Preorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/">
 *   1028. Recover a Tree From Preorder Traversal</a>
 */
public final class Q1028 {
    public TreeNode recoverFromPreorder(String traversal) {
        // 1. The number of nodes in the original tree is in the range [1, 1000].
        // 2. 1 <= Node.val <= 10^9
        // If a node has only one child, that child is guaranteed to be the left child.
        Stack<TreeNode> stack = new Stack<>();
        final int len = traversal.length();
        int p = 0;
        while (p < traversal.length()) {
            int depth = 0;
            while (traversal.charAt(p) == '-') {
                depth++; p++;
            }

            int val = 0;
            char ch;
            while (p < len && (ch = traversal.charAt(p)) != '-') {
                val = val * 10 + (ch - '0');
                p++;
            }

            final TreeNode node = new TreeNode(val);
            if (depth == 0) {
                // root;
                stack.add(node);
            } else {
                while (stack.size() > depth) stack.pop();
                final TreeNode parent = stack.peek();
                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
                stack.add(node);
            }
        }

        while (stack.size() != 1) stack.pop();
        return stack.pop();
    }
}
