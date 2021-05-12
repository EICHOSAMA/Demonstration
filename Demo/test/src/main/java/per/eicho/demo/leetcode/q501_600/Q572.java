package per.eicho.demo.leetcode.q501_600;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>572. Subtree of Another Tree 的题解代码 </p>
 * 
 * <pre>
 *  Given the roots of two binary trees root and subRoot, 
 *  return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 *  A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. 
 *  The tree tree could also be considered as a subtree of itself.
 * 
 * Example 1:
 *    Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 *    Output: true
 * 
 * Example 2:
 *    Input: root = [3,4,5,1,2,null,null,0], subRoot = [4,1,2]
 *    Output: false
 * 
 * Constraints:
 *    1. 1 <= n <= 10^4
 *    2. nums.length == 2 * n
 *    3. -10^4 <= nums[i] <= 10^4
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/subtree-of-another-tree/">572. Subtree of Another Tree</a>
 */
final public class Q572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null) {
            return false;
        }

        if (validate(root, subRoot)) {
            return true;
        }

        if (isSubtree(root.left, subRoot)) {
            return true;
        }

        if (isSubtree(root.right, subRoot)) {
            return true;
        }

        return false;
    }

    public boolean validate(TreeNode root, TreeNode subRoot) {

        if (root == null || subRoot == null) {
            return root == subRoot;
        }

        if (root.val != subRoot.val) {
            return false;
        }

        if (!validate(root.left, subRoot.left)) {
            return false;
        }

        if (!validate(root.right, subRoot.right)) {
            return false;
        }

        return true;
    }
}
