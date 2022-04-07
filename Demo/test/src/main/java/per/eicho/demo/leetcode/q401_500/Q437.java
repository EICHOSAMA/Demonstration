package per.eicho.demo.leetcode.q401_500;

import java.util.HashMap;
import java.util.Map;

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
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode node, Map<Long, Integer> prefix, long currentSum, int targetSum) {
        if (node == null) return 0;

        int result = 0;
        currentSum += node.val;

        result = prefix.getOrDefault(currentSum - targetSum, 0);
        prefix.put(currentSum, prefix.getOrDefault(currentSum, 0) + 1);
        result += dfs(node.left, prefix, currentSum, targetSum);
        result += dfs(node.right, prefix, currentSum, targetSum);
        prefix.put(currentSum, prefix.get(currentSum) - 1);

        return result;
    }
}
