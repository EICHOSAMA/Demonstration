package per.eicho.demo.leetcode.q501_600;

import java.util.HashMap;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>508. Most Frequent Subtree Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/most-frequent-subtree-sum/">
 *   508. Most Frequent Subtree Sum</a>
 */
public class Q508 {

    Map<Integer, int[]> map;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. -10^5 <= Node.val <= 10^5
        map = new HashMap<>();
        dfs(root);

        int max = 0;
        int count = 0;
        for (int[] countInfo : map.values()) {
            if (countInfo[0] < max) continue;
            if (countInfo[0] == max) {
                count++;
            } else {
                max = countInfo[0];
                count = 1;
            }
        }

        final int[] result = new int[count];
        int p = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (entry.getValue()[0] == max) result[p++] = entry.getKey();
        }
        return result;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        final int sum = node.val + dfs(node.left) + dfs(node.right);
        return count(sum);
    }

    private int count(int sum) {
        if (!map.containsKey(sum)) map.put(sum, new int[]{0});
        map.get(sum)[0]++;
        return sum;
    }
}
