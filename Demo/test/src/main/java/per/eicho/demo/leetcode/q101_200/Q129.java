package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>129. Sum Root to Leaf Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-root-to-leaf-numbers/">129. Sum Root to Leaf Numbers</a>
 */
public final class Q129 {
    public int sumNumbers(TreeNode root) {
        return traversal(root, 0);
    }

    private int traversal(final TreeNode root, int num) {
        if (root == null) return 0;

        num *= 10;
        num += root.val;
        
        if (root.left == null && root.right == null) return num;
        return traversal(root.left, num) + traversal(root.right, num);
    }
}
