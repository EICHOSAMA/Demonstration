package per.eicho.demo.leetcode.q1301_1400;

import java.util.HashMap;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1339. Maximum Product of Splitted Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/">
 *   1339. Maximum Product of Splitted Binary Tree</a>
 */
public final class Q1339 {

    Map<TreeNode, Integer> sumMap;
    int sumOfRoot;
    long result = 0;

    public int maxProduct(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [2, 5 * 10^4].
        // 2. 1 <= Node.val <= 10^4
        sumMap = new HashMap<>();
        sumOfRoot = summarize(root);
        traversal(root);
        return (int)(result % 1_000_000_007L);
    }

    private void traversal(TreeNode node) {
        if (node.left != null) {
            final long sum1 = sumMap.get(node.left);
            final long sum2 = sumOfRoot - sum1;
            final long candidate = sum1 * sum2;
            result = Math.max(result, candidate);
            traversal(node.left);
        }

        if (node.right != null) {
            final long sum1 = sumMap.get(node.right);
            final long sum2 = sumOfRoot - sum1;
            final long candidate = sum1 * sum2;
            result = Math.max(result, candidate);
            traversal(node.right);
        }
    }

    private int summarize(TreeNode node) {
        if (node == null) return 0;
        int sum = node.val + summarize(node.left) + summarize(node.right);
        sumMap.put(node, sum);
        return sum;
    }
}
