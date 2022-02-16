package per.eicho.demo.leetcode.q1001_1100;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1022. Sum of Root To Leaf Binary Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/">
 *   1022. Sum of Root To Leaf Binary Numbers</a>
 */
public final class Q1022 {
    public int sumRootToLeaf(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 1000].
        // 2. Node.val is 0 or 1.
        return sum(root, 0);
    }

    private int sum(TreeNode node, int prefix) {
        prefix = prefix << 1 + node.val;
        if (node.left == null && node.right == null) {
            System.out.println(prefix);
            return prefix;
        }

        int sum = 0;
        if (node.left != null) sum += sum(node.left, prefix);
        if (node.right != null) sum += sum(node.right, prefix);
        
        return sum;
    }
}
