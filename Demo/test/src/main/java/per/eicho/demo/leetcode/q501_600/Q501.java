package per.eicho.demo.leetcode.q501_600;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>501. Find Mode in Binary Search Tree 的题解代码 </p>
 * 
 * <pre>
 *  Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 *  If the tree has more than one mode, return them in any order.
 *  Assume a BST is defined as follows:
 *    1. The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 *    2. The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 *    3. Both the left and right subtrees must also be binary search trees.
 * 
 * Example 1: 
 *    Input: root = [1,null,2,2]
 *    Output: [2]
 * 
 * Example 2:
 *    Input: root = [0]
 *    Output: [0]
 * 
 *  Constraints:
 *   1. The number of nodes in the tree is in the range [1, 104].
 *   2. -105 <= Node.val <= 105
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/">501. Find Mode in Binary Search Tree</a>
 */
final public class Q501 {
    final int LEFT_BOUND = -100000;
    final int RIGHT_BOUND = 100000;
    final int OFF_SET = 100000;

    public int[] findMode(TreeNode root) {
        final int[] counts = new int[RIGHT_BOUND - LEFT_BOUND + 1];

        //assert root != null;
        // 1. traverse the whole tree and count the frequence
        inorderTraverse(root, counts);

        // 2. find max element in counts array.
        final int maxFrequence = getMaxElement(counts);
        
        // 3. generate result.
        final List<Integer> tempResult = new ArrayList<>();        
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == maxFrequence) {
                tempResult.add(i - OFF_SET);
            }
        }

        return tempResult.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void inorderTraverse(final TreeNode node, final int[] counts) {
        if (node.left != null) {
            inorderTraverse(node.left, counts);
        }

        final int index = node.val + OFF_SET;
        counts[index]++;

        if (node.right != null) {
            inorderTraverse(node.right, counts);
        }
    }

    private int getMaxElement(final int[] nums) {
        int max = 0;

        for (int num: nums) {
            if (num > max) {
                max = num;
            } 
        }

        return max;
    }
}
