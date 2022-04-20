package per.eicho.demo.algorithm.tree.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;
import per.eicho.demo.leetcode.q001_100.Q94;
import per.eicho.demo.leetcode.q101_200.Q144;

/**
 * <p>二叉树遍历 之 莫里斯遍历</p>
 * 
 * <pre>
 *   Morris遍历常数空间遍历法 (J.H.Morris在1979年的论文「Traversing Binary Trees Simply and Cheaply」中首次提出)
 *   
 *   本样例类中将手撸基于Morris遍历思想而实现的三种遍历。
 *   
 *     1. 前序遍历 (Pre-Order Traversal)
 *     2. 中序遍历 (In-Order Traversal)
 *     3. 后续遍历 (Post-Order Traversal)
 * </pre>
 * 
 */
public final class MorrisTraversalSample {

    /**
     * <p>前序遍历</p>
     * 
     * <pre>
     *  指先访问根，然后访问左右子树的遍历方式
     * </pre>
     * 
     * @see {@link Q144 144. Binary Tree Preorder Traversal}
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        final List<Integer> result = new ArrayList<>();

        TreeNode cursor = root;
        while (cursor != null) {
            if (hasLeftSubTree(cursor)) {
                // rmn: right most node.
                TreeNode rmn = findRightMostNodeOfLeftSubTree(cursor);

                if (!isRMNConnected(rmn)) {
                    result.add(cursor.val);
                    connectRMN(rmn, cursor);
                    cursor = cursor.left;
                } else {
                    disConnectRMN(rmn);
                    cursor = cursor.right;
                }
            } else {
                result.add(cursor.val);
                cursor = cursor.right;
            }
        }
        return result;
    } 

    /**
     * <p>中序遍历</p>
     * 
     * <pre>
     *  指先访问左（右）子树，然后访问根，最后访问右（左）子树的遍历方式
     * </pre>
     * 
     * @see {@link Q94 94. Binary Tree Inorder Traversal}
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        final List<Integer> result = new ArrayList<>();

        TreeNode cursor = root;
        while (cursor != null) {
            if (hasLeftSubTree(cursor)) {
                // rmn: right most node.
                TreeNode rmn = findRightMostNodeOfLeftSubTree(cursor);
                if (!isRMNConnected(rmn)) {
                    connectRMN(rmn, cursor);
                    cursor = cursor.left;
                } else {
                    disConnectRMN(rmn);
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

    /**
     * <p>后序遍历</p>
     * 
     * <pre>
     *  指先访问左右子树，然后访问根的遍历方式
     * </pre>
     * 
     * @see {@link Q145 Q145. Binary Tree Postorder Traversal}
     */    
    public static List<Integer> postorderTraversal(TreeNode root) {
        final LinkedList<Integer> result = new LinkedList<>();

        TreeNode cursor = root;
        while (cursor != null) {
            if (hasRightSubTree(cursor)) {
                // rmn: right most node.
                TreeNode lmn = findLeftMostNodeOfRightSubTree(cursor);

                if (!isLMNConnected(lmn)) {
                    result.addFirst(cursor.val);
                    connectLMN(lmn, cursor);
                    cursor = cursor.right;
                } else {
                    disConnectLMN(lmn);
                    cursor = cursor.left;
                }
            } else {
                result.addFirst(cursor.val);
                cursor = cursor.left;
            }
        }

        return result;
    } 

    private static boolean hasLeftSubTree(TreeNode node) {
        return node.left != null;
    }

    private static TreeNode findRightMostNodeOfLeftSubTree(TreeNode root) {
        TreeNode cursor = root.left;
        while (cursor.right != null && cursor.right != root) {
            cursor = cursor.right;
        }
        return cursor;
    }

    private static boolean isRMNConnected(TreeNode rightMostNode) {
        return rightMostNode.right != null;
    }

    private static void connectRMN(TreeNode rightMostNode, TreeNode root) {
        rightMostNode.right = root;
    }

    private static void disConnectRMN(TreeNode rightMostNode) {
        rightMostNode.right = null;
    }



    private static boolean hasRightSubTree(TreeNode node) {
        return node.right != null;
    }
    
    private static TreeNode findLeftMostNodeOfRightSubTree(TreeNode root) {
        TreeNode cursor = root.right;
        while (cursor.left != null && cursor.left != root) {
            cursor = cursor.left;
        }
        return cursor;
    }

    private static boolean isLMNConnected(TreeNode leftMostNode) {
        return leftMostNode.left != null;
    }

    private static void connectLMN(TreeNode leftMostNode, TreeNode root) {
        leftMostNode.left = root;
    }

    private static void disConnectLMN(TreeNode leftMostNode) {
        leftMostNode.left = null;
    }
}
