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

    public List<Integer> inorderTraversal(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 100].
        // 2. -100 <= Node.val <= 100        
        final List<Integer> result = new ArrayList<>();

        TreeNode cursor = root;

        while (cursor != null) {
            if (hasLeftSubTree(cursor)) {
                TreeNode rmn = findRightMostNodeOfLeftSubTree(cursor);
                if (!isConnected(rmn)) {
                    connect(rmn, cursor);
                    cursor = cursor.left;
                } else {
                    // is connected
                    disConnect(rmn);
                    result.add(cursor.val);
                    cursor = cursor.right;
                }
            } else {
                result.add(cursor.val);
                cursor = cursor.right;
            }
        }
        return result;
    }

    private boolean hasLeftSubTree(TreeNode node) {
        return node.left != null;
    }

    private TreeNode findRightMostNodeOfLeftSubTree(TreeNode root) {
        TreeNode cursor = root.left;
        while (cursor.right != null && cursor.right != root) {
            cursor = cursor.right;
        }
        return cursor;
    }

    private boolean isConnected(TreeNode rightMostNode) {
        return rightMostNode.right != null;
    }

    private void connect(TreeNode rightMostNode, TreeNode root) {
        rightMostNode.right = root;
    }

    private void disConnect(TreeNode rightMostNode) {
        rightMostNode.right = null;
    }

    public static void main(String[] args) {
        Q94 q94 = new Q94();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        for (Integer num : q94.inorderTraversal(root)) {
            System.out.println("→" + num);
        }
    }
}
