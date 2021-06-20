package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>145. Binary Tree Postorder Traversal 的题解代码 </p>
 * 
 * <pre>
 *  Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * 
 * Example 1: 
 *    Input: root = [1,null,2,3]
 *    Output: [3,2,1]
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
 *    Output: [2,1]
 * 
 *  Constraints:
 *   1. The number of nodes in the tree is in the range [0, 100].
 *   2. -100 <= Node.val <= 100
 * </pre>
 * @see <a href="https://leetcode.com/problems/binary-tree-postorder-traversal/">145. Binary Tree Postorder Traversal</a>
 */
final public class Q145 {

    /**
     * <p>inorder traversal</p>
     * 
     * <pre>
     *  do postorder traversal for the given binary tree.
     * 
     *  postorder traversal is a kind of depth first traversals,
     *  which traversal the tree using left, right, root order. 
     * </pre>
     * 
     * @param root
     * @return
     * 
     * @link https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
     */    
    public List<Integer> postorderTraversal(TreeNode root) {
        final List<Integer> result = new ArrayList<>();

        if (root != null) {
            dopostorderTraversal(root, result);
        }

        return result;
    }

    private void dopostorderTraversal(final TreeNode node, final List<Integer> result) {
        //assert node != null;

        if (node.left != null) {
            dopostorderTraversal(node.left, result);
        }

        if (node.right != null) {
            dopostorderTraversal(node.right, result);
        }        

        result.add(node.val);
    }
}
