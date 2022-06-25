package per.eicho.demo.leetcode.q1101_1200;

import java.util.LinkedList;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1161. Maximum Level Sum of a Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/">
 *   1161. Maximum Level Sum of a Binary Tree</a>
 */
public final class Q1161 {
    public int maxLevelSum(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. -10^5 <= Node.val <= 10^5
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxSum = root.val;
        int lv = 1;
        int maxLv = 1;
        while (!queue.isEmpty()) {
            final int layerSize = queue.size();

            int sum = 0;
            for (int i = 0; i < layerSize; i++) {
                final TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

            }

            if (sum > maxSum) {
                maxSum = sum;
                maxLv = lv;
            }
            lv++;
        }

        return maxLv;
    }
}
