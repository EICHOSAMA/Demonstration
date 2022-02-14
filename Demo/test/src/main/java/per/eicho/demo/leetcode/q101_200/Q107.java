package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>107. Binary Tree Level Order Traversal II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii/">
 *   107. Binary Tree Level Order Traversal II</a>
 */
public final class Q107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 2000].
        // 2. -1000 <= Node.val <= 1000        
        LinkedList<List<Integer>> reversedResult = new LinkedList<>();
        if (root == null) return reversedResult;

        List<TreeNode> current = new LinkedList<>();
        current.add(root);
        List<TreeNode> next = new LinkedList<>();
        while (current.size() != 0) {
            List<Integer> answer = new LinkedList<>();
            while (current.size() != 0) {
                TreeNode node  = current.remove(0);
                answer.add(node.val);
                if (node.left != null)
                    next.add(node.left);
                if (node.right != null)
                    next.add(node.right);
            }
            reversedResult.add(answer);

            List<TreeNode> temp = current;
            current = next;
            next =  temp;
        }

        final List<List<Integer>> result = new ArrayList<>(reversedResult.size());
        while (reversedResult.size() != 0) {
            result.add(reversedResult.pollLast());
        }
        return result;
    }
}
