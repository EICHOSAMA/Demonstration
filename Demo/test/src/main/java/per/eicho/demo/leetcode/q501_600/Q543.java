package per.eicho.demo.leetcode.q501_600;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>543. Diameter of Binary Tree 的题解代码 </p>
 * 
 * <pre>
 *  Given the root of a binary tree, return the length of the diameter of the tree.
 *  The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *  The length of a path between two nodes is represented by the number of edges between them.
 * 
 * Example 1: 
 *    Input: root = [1,2,3,4,5]
 *    Output: 3
 *    Explanation: 3is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * Example 2:
 *    Input: root = [1,2]
 *    Output: 1
 * 
 *  Constraints:
 *    1. The number of nodes in the tree is in the range [2, 10^4].
 *    2. 0 <= Node.val <= 10^5
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/diameter-of-binary-tree/">543. Diameter of Binary Tree</a>
 */
final public class Q543 {
    
    /**
     * a field to save the result of this answer.
     */
    int result = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        doPostorderTraversal(root);
        return result;
    }

    /**
     * <p>poster order traversal</p>
     * 
     * <pre>
     *  traversal the given node, to find out the depth of the given node.
     * 
     *  side effect, calling this method may cause the value of {@link #result} to be changed.
     * </pre>
     * 
     * @param node target node.
     * @return depth of the given node.
     */
    private int doPostorderTraversal(final TreeNode node) {
        
        if (node == null) {
            return 0;
        }

        final int leftDepth = doPostorderTraversal(node.left);
        final int rightDepth = doPostorderTraversal(node.right); 

        final int pathLength = leftDepth + rightDepth;

        if (pathLength > result) {
            result = pathLength;
        }
        
        return Math.max(leftDepth, rightDepth) + 1;
    }

}
