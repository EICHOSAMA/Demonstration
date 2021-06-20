package per.eicho.demo.leetcode.q501_600;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>530. Minimum Absolute Difference in BST 的题解代码 </p>
 * 
 * <pre>
 *  Given the root of a Binary Search Tree (BST), 
 *  return the minimum absolute difference between the values of any two different nodes in the tree.
 * 
 * Example 1: 
 *    Input: root = [4,2,6,1,3]
 *    Output: 1
 * 
 * Example 2:
 *    Input: root = [1,0,48,null,null,12,49]
 *    Output: 1
 * 
 *  Constraints:
 *    1. The number of nodes in the tree is in the range [2, 10^4].
 *    2. 0 <= Node.val <= 10^5
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/minimum-absolute-difference-in-bst/">530. Minimum Absolute Difference in BST</a>
 */
final public class Q530 {
    public int getMinimumDifference(TreeNode root) {
        final List<Integer> treeList = new ArrayList<>();

        // 1. tree ⇒ treeList, treeList is in ascending order.
        doInorderTraversal(root, treeList);

        // 2. 
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < treeList.size() - 1; i++) {
            final int num1 = treeList.get(i);
            final int num2 = treeList.get(i + 1);

            //assert num2 >= num1;
            final int difference = num2 - num1;
            if (difference < min) {
                min = difference;
            }
        }

        return min;
    }

    private void doInorderTraversal(final TreeNode node, final List<Integer> result) {
        if (node.left != null) {
            doInorderTraversal(node.left, result);
        }

        result.add(node.val);

        if (node.right != null) {
            doInorderTraversal(node.right, result);
        }
    }
}
