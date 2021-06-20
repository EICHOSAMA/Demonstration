package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>104. Maximum Depth of Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">104. Maximum Depth of Binary Tree</a>
 */
final public class Q104 {
    
    private int maxDepth = 0;

    public int maxDepth(TreeNode root) {
        walkThrough(root, 0);
        return maxDepth;
    }

    public void walkThrough(TreeNode root, int level) {
        if (root == null) {
            if (level > maxDepth)
                maxDepth = level;
            return;
        }
        walkThrough(root.left, level + 1);
        walkThrough(root.right, level + 1);
    }
}
