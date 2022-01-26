package per.eicho.demo.leetcode.q1301_1400;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1305. All Elements in Two Binary Search Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-in-binary-tree/">1305. All Elements in Two Binary Search Trees</a>
 */
public final class Q1305 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // 1. The number of nodes in each tree is in the range [0, 5000].
        // 2. -10^5 <= Node.val <= 10^5

        List<Integer> l1 = flatBinaryTree(root1);
        List<Integer> l2 = flatBinaryTree(root2);

        // merge two list
        List<Integer> result = new LinkedList<>();
        Iterator<Integer> p1 = l1.iterator();
        Iterator<Integer> p2 = l2.iterator();
        Integer num1 = p1.hasNext() ? p1.next() : null;
        Integer num2 = p2.hasNext() ? p2.next() : null;
        final int count = l1.size() + l2.size();

        while (result.size() != count) {
            if (num1 == null) {
                result.add(num2);
                num2 = null;
                while (p2.hasNext()) {
                    result.add(p2.next());
                }
                continue;
            }

            if (num2 == null) {
                result.add(num1);
                num1 = null;
                while (p1.hasNext()) {
                    result.add(p1.next());
                }
            }

            if (num1 < num2) {
                result.add(num1);
                num1 = null;
                if (p1.hasNext()) {
                    num1 = p1.next();
                }
            } else {
                result.add(num2);
                num2 = null;
                if (p2.hasNext()) {
                    num2 = p2.next();
                }
            }
        }

        return result;
    }

    private List<Integer> flatBinaryTree(TreeNode root) {
        List<Integer> nums = new LinkedList<>();
        flatBinaryTree(root, nums);
        return nums;
    }

    private void flatBinaryTree(TreeNode node, List<Integer> nums) {
        if (node == null) return;

        flatBinaryTree(node.left, nums);
        nums.add(node.val);
        flatBinaryTree(node.right, nums);
    }
}
