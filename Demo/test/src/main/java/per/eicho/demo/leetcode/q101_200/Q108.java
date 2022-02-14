package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>108. Convert Sorted Array to Binary Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/">
 *   108. Convert Sorted Array to Binary Search Tree</a>
 */
public final class Q108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^4 <= nums[i] <= 10^4
        // 3. nums is sorted in a strictly increasing order.        
        if (nums == null || nums.length == 0) return null;
        return partOfSortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode partOfSortedArrayToBST(final int[] nums,
                                            final int l,
                                            final int r) {
        if (r < l) return null;

        final int mid = (l + r + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = partOfSortedArrayToBST(nums, l, mid - 1);
        root.right = partOfSortedArrayToBST(nums, mid + 1, r);
        return root;
    }
}
