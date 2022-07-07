package per.eicho.demo.leetcode.q1101_1200;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1110. Delete Nodes And Return Forest 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/delete-nodes-and-return-forest/">
 *   1110. Delete Nodes And Return Forest</a>
 */
public final class Q1110 {

    private List<TreeNode> result;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // 1. The number of nodes in the given tree is at most 1000.
        // 2. Each node has a distinct value between 1 and 1000.
        // 3. to_delete.length <= 1000
        // 4. to_delete contains distinct values between 1 and 1000.   
        result = new LinkedList<>();
        final Set<Integer> toDelete = new HashSet<>();
        for (int val : to_delete) toDelete.add(val);
        traversal(root, toDelete, true);
        return result;
    }

    private TreeNode traversal(TreeNode node, Set<Integer> toDelete, boolean root) {
        if (node == null) return null;

        final boolean target = toDelete.contains(node.val);
        
        if (target) {
            traversal(node.left, toDelete, true);
            traversal(node.right, toDelete, true);
            return null;
        } else {
            node.left = traversal(node.left, toDelete, false);
            node.right = traversal(node.right, toDelete, false);
            if (root) result.add(node);
            return node;
        }
    }
}
