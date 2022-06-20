package per.eicho.demo.leetcode.q901_1000;

import java.util.HashMap;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>968. Binary Tree Cameras 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-cameras/">
 *   968. Binary Tree Cameras</a>
 */
public final class Q968 {

    Map<TreeNode, int[]> memo;
    private static final int DEFAULT_VALUE = 1_000_000;

    public int minCameraCover(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 1000].
        // 2. Node.val == 0
        memo = new HashMap<>();
        init(root);
        return search(root, 0);
    }

    private void init(TreeNode node) {
        if (node == null) return;
        if (isLeaf(node)) {
            memo.put(node, new int[]{1, 0, 1});
        } else {
            memo.put(node, new int[]{DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE});
            init(node.left);
            init(node.right);
        }
    }

    private int search(TreeNode node, int status) {
        if (node == null) return 0;
        if (memo.get(node)[status] != DEFAULT_VALUE) return memo.get(node)[status];
        // if (alreadyBeenMonitted

        int f = DEFAULT_VALUE;
        if (status == 0) {
            f = search(node, 2); // set camera
            // not set
            if (node.left != null && node.right != null) {
                f = Math.min(f, search(node.left, 2) + search(node.right, 0));
                f = Math.min(f, search(node.left, 0) + search(node.right, 2));
            } else if (node.right == null) {
                // node.left != null && node.right == null;
                f = Math.min(f, search(node.left, 2));
            } else {
                // node.left == null && node.right != null;
                f = Math.min(f, search(node.right, 2));
            }
        } else if (status == 1) {
            f = search(node, 2); // set camera
            // not set.
            f = Math.min(f, search(node.left, 0) + search(node.right, 0));
        } else {
            // status == 2
            f = 1 + search(node.left, 1) + search(node.right, 1);
        }
        return memo.get(node)[status] = f;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
