package per.eicho.demo.leetcode.q1301_1400;

import java.util.LinkedList;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1302. Deepest Leaves Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/deepest-leaves-sum/">
 *   1302. Deepest Leaves Sum</a>
 */
public final class Q1302 {
    public int deepestLeavesSum(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. 1 <= Node.val <= 100
        final Queue<TreeNode> queue = new LinkedList<>();

        int sum = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            sum = 0;
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return sum;
    }
}
