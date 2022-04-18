package per.eicho.demo.leetcode.q201_300;

import java.util.Deque;
import java.util.LinkedList;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>230. Kth Smallest Element in a BST 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/">
 *   230. Kth Smallest Element in a BST</a>
 */
public final class Q230 {
    public int kthSmallest(TreeNode root, int k) {
        // 1. The number of nodes in the tree is n.
        // 2. 1 <= k <= n <= 10^4
        // 3. 0 <= Node.val <= 10^4        
        //assert k >= 1 && root != null: "1 <= k <= n";

        // 1. construct a stack containg all left node.
        final Deque<TreeNode> stack = new LinkedList<>();
        addAllLeftNodeToStack(stack, root);

        int currentRank = 1; // represent the rank to the last(current/processing) node in the stack.
        while (currentRank != k) {
            final TreeNode currentNode = stack.pollLast();
            currentRank++; // rank ++;
            if (currentNode.right == null) continue;
            addAllLeftNodeToStack(stack, currentNode.right);
        }
        return stack.getLast().val;
    }

    private void addAllLeftNodeToStack(final Deque<TreeNode> stack, final TreeNode root) {
        for (TreeNode cursor = root; cursor != null; cursor = cursor.left) stack.addLast(cursor);
    }
}
