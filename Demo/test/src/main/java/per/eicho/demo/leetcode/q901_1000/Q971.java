package per.eicho.demo.leetcode.q901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>971. Flip Binary Tree To Match Preorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/">
 *   971. Flip Binary Tree To Match Preorder Traversal</a>
 */
public final class Q971 {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        // 1. The number of nodes in the tree is n.
        // 2. n == voyage.length
        // 3. 1 <= n <= 100
        // 4. 1 <= Node.val, voyage[i] <= n
        // 5. All the values in the tree are unique.
        // 6. All the values in voyage are unique.        
        final List<Integer> result = new ArrayList<>();
        final Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        final int n = voyage.length;
        int p = 0;
        while (p < n) {
            final TreeNode node = stack.pop();
            if (node.val != voyage[p]) return Arrays.asList(-1);
            
            p++;
            if (p >= n) break;

            if (node.left == null && node.right == null) continue;
            if (node.left != null && node.right != null) {
                if (node.left.val != voyage[p]) {
                    if (node.right.val != voyage[p]) return Arrays.asList(-1);
                    final TreeNode temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                    result.add(node.val);      
                }

                stack.push(node.right);
                stack.push(node.left);
                continue;
            }

            final TreeNode child = node.left != null ? node.left : node.right;
            stack.push(child);
        }

        return result;
    }
}
