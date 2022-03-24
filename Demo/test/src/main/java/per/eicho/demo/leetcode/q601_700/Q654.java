package per.eicho.demo.leetcode.q601_700;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>654. Maximum Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-binary-tree/">654. Maximum Binary Tree</a>
 */
public final class Q654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 0 <= nums[i] <= 1000
        // 3. All integers in nums are unique.        
        final int n = nums.length;
        final Stack<TreeNode> monoStack = new Stack<>();

        monoStack.add(new TreeNode(nums[0]));
        for (int i = 1; i < n; i++) {
            final int num = nums[i];
            final TreeNode node = new TreeNode(num);

            TreeNode subRoot = null;
            while (!monoStack.isEmpty() && monoStack.peek().val < node.val) {
                TreeNode poppedNode = monoStack.pop();
                poppedNode.right = subRoot;
                subRoot = poppedNode;
            }
            node.left = subRoot;

            // monoStack isEmpty Or monoStack.peek().val > node.val
            monoStack.add(node);
        }

        TreeNode root = null;
        while (!monoStack.isEmpty()) {
            TreeNode poppedNode = monoStack.pop();
            poppedNode.right = root;
            root = poppedNode;
        }
        return root;
    }
}
