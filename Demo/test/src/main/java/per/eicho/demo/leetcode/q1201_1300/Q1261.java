package per.eicho.demo.leetcode.q1201_1300;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1261. Find Elements in a Contaminated Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/">
 *   1261. Find Elements in a Contaminated Binary Tree</a>
 */
@SuppressWarnings("unused")
public final class Q1261 {
    private static class FindElements {

        final TreeNode root;

        public FindElements(TreeNode root) {
            this.root = root;
            root.val = 0;
            recover(root);
        }

        private void recover(TreeNode node) {
            if (node.left != null) {
                node.left.val = node.val * 2 + 1;
                recover(node.left);
            }

            if (node.right != null) {
                node.right.val = node.val * 2 + 2;
                recover(node.right);
            }
        }
        
        public boolean find(int target) {
            return findNode(target) != null;
        }

        private TreeNode findNode(int target) {
            if (target == 0) return root;
            final int parentVal = (target - 1) / 2;
            final TreeNode parent = findNode(parentVal);
            if (parent == null) return null;
            return parentVal * 2 + 1 == target ? parent.left : parent.right;
        }
    }
}
