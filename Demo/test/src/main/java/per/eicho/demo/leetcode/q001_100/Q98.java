package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>98. Validate Binary Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/validate-binary-search-tree/">98. Validate Binary Search Tree</a>
 */
final public class Q98 {

    private final static class Q98Triple {
        private final boolean isValidBST;
        private final int min;
        private final int max;

        Q98Triple(boolean isValidBST, int min, int max) {
            this.isValidBST = isValidBST;
            this.min = min;
            this.max = max;
        }

        static final Q98Triple FAILED = new Q98Triple(false, 0, 0);
    }

    public boolean isValidBST(TreeNode root) {
        // A valid BST is defined as follows:
        //   1. The left subtree of a node contains only nodes with keys less than the node's key.
        //   2. The right subtree of a node contains only nodes with keys greater than the node's key.
        //   3. Both the left and right subtrees must also be binary search trees.
        //return isValidBST(root, null, false);
        return _isValidBST(root).isValidBST;
    }
    private Q98Triple _isValidBST(final TreeNode root) {
        int min = root.val;
        int max = root.val;


        // 1. check left subtree
        if (root.left != null) {
            Q98Triple lResult = _isValidBST(root.left);
            
            if (!lResult.isValidBST) {
                return Q98Triple.FAILED; // failed
            } else if (lResult.max >= root.val) {
                return Q98Triple.FAILED; // failed
            }

            min = lResult.min;
        }

        // 2. check right subtree
        if (root.right != null) {
            Q98Triple RResult = _isValidBST(root.right);
            
            if (!RResult.isValidBST) {
                return Q98Triple.FAILED; // failed
            } else if (RResult.min <= root.val) {
                return Q98Triple.FAILED; // failed
            }

            max = RResult.max;
        }

        return new Q98Triple(true, min, max);
    }
}
