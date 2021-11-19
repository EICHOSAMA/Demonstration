package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>94. Binary Tree Inorder Traversal 的题解代码 </p>
 * 
 * <pre>
 *  Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * 
 * Example 1: 
 *    Input: root = [1,null,2,3]
 *    Output: [1,3,2]
 * 
 * Example 2:
 *    Input: root = []
 *    Output: []
 * 
 * Example 3:
 *    Input: root = [1]
 *    Output: [1]
 * 
 * Example 4:
 *    Input: root = [1,2]
 *    Output: [2,1]
 * 
 * Example 5:
 *    Input: root = [1,null,2]
 *    Output: [1,2]
 * 
 *  Constraints:
 *   1. The number of nodes in the tree is in the range [0, 100].
 *   2. -100 <= Node.val <= 100
 * </pre>
 * @see <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/">94. Binary Tree Inorder Traversal</a>
 */
final public class Q94 {

    /**
     * <p>inorder traversal</p>
     * 
     * <pre>
     *  do inorder traversal for the given binary tree.
     * 
     *  inorder traversal is a kind of depth first traversals,
     *  which traversal the tree using left, root, right order. 
     * </pre>
     * 
     * @param root
     * @return
     * 
     * @link https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        final List<Integer> result = new ArrayList<>();

        if (root != null) {
            doInorderTraversal(root, result);
        }

        return result;
    }

    private void doInorderTraversal(final TreeNode node, final List<Integer> result) {
        //assert node != null;

        if (node.left != null) {
            doInorderTraversal(node.left, result);
        }

        result.add(node.val);

        if (node.right != null) {
            doInorderTraversal(node.right, result);
        }
    }
}
