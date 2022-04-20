package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>144. Binary Tree Preorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/">144. Binary Tree Preorder Traversal</a>
 */
public final class Q144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 100].
        // 2. -100 <= Node.val <= 100           
        List<Integer> result = new ArrayList<>();

        TreeNode cursor = root;
        while (cursor != null) {
            if (hasLeftSubTree(cursor)) {
                // rmn: right most node.
                TreeNode rmn = findRightMostNodeOfLeftSubTree(cursor);

                if (!isConnected(rmn)) {
                    result.add(cursor.val);
                    connect(rmn, cursor);
                    cursor = cursor.left;
                } else {
                    disConnect(rmn);
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
}
