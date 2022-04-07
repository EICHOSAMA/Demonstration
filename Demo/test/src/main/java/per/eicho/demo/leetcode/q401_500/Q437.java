package per.eicho.demo.leetcode.q401_500;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>437. Path Sum III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/path-sum-iii/">437. Path Sum III</a>
 */
public final class Q437 {
    public int pathSum(TreeNode root, int targetSum) {
        // 1. The number of nodes in the tree is in the range [0, 1000].
        // 2. -10^9 <= Node.val <= 10^9
        // 3. -1000 <= targetSum <= 1000
        return pathSum(root, (long)targetSum, false);
    }

    private int pathSum(TreeNode node, long targetSum, boolean needContinuous) {
        if (node == null) return 0;

        int result = 0;

        if (needContinuous) {
            // use
            result += pathSum(node.left, targetSum - node.val, true);
            result += pathSum(node.right, targetSum - node.val, true);
        } else {
            // not use.
            result += pathSum(node.left, targetSum, false);
            result += pathSum(node.right, targetSum, false);
            // use
            result += pathSum(node.left, targetSum - node.val, true);
            result += pathSum(node.right, targetSum - node.val, true);
        }

        if (targetSum == (long)node.val) result++;

        System.out.println(node.val + ": target:" + targetSum + " res:" + result);

        return result;
    }
}
