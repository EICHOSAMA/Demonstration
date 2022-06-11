package per.eicho.demo.leetcode.q601_700;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>652. Find Duplicate Subtrees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-duplicate-subtrees/">
 *   652. Find Duplicate Subtrees</a>
 */
public final class Q652 {

    Map<String, Integer> map;
    Map<Integer, int[]> count;
    int id = 1;
    List<TreeNode> result = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // 1. The number of the nodes in the tree will be in the range [1, 10^4]
        // 2. -200 <= Node.val <= 200
        map = new HashMap<>();
        count = new HashMap<>();
        traversal(root);
        return result;
    }

    private int traversal(TreeNode node) {
        if (node == null) return 0;
        final String key = node.val + "|" + traversal(node.left) + "|" + traversal(node.right);
        if (map.containsKey(key)) {
            final int nID = map.get(key);
            count.get(nID)[0]++;

            if (count.get(nID)[0] == 2) result.add(node);
            return nID;
        } else {
            map.put(key, id);
            count.put(id, new int[]{1});
            return id++;
        }
    }
}
