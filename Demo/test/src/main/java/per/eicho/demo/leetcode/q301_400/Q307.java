package per.eicho.demo.leetcode.q301_400;

import per.eicho.demo.datastructure.tree.binarytree.segmenttree.SegementTree;

/**
 * <p>307. Range Sum Query - Mutable 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/range-sum-query-mutable/">
 *   307. Range Sum Query - Mutable</a>
 * @see {@link SegementTree 线段树Sample类}
 */
@SuppressWarnings("unused")
public final class Q307 {

    private static final class NumArray {

        private final static class TreeNode {
            final int l;
            final int r;
            int sum;
    
            TreeNode left;
            TreeNode right;
    
            private TreeNode(int l, int r) {
                // assert l <= r: "由construceSegmentTree方法保证";
                this.l = l;
                this.r = r;
            }
    
            static TreeNode construceSegmentTree(int[] nums) {
                return construceSegmentTree(nums, 0, nums.length - 1);
            }
    
            private static TreeNode construceSegmentTree(int[] nums, int l ,int r) {
                final TreeNode treeNode = new TreeNode(l, r);
                if (l == r) {
                    treeNode.sum = nums[l];
                    return treeNode;
                }
    
                final int mid = (l + r + 1) / 2;
                treeNode.left = construceSegmentTree(nums, l, mid - 1);
                treeNode.right = construceSegmentTree(nums, mid, r);
                treeNode.sum = treeNode.left.sum + treeNode.right.sum;
                return treeNode;
            }
    
            private boolean isLeafNode() { return l == r; }
        }

        final TreeNode root;

        /**
         * Initializes the object with the integer array nums.
         */
        public NumArray(int[] nums) {
            root = TreeNode.construceSegmentTree(nums);
        }
        
        /**
         * Updates the value of nums[index] to be val.
         */
        public void update(int index, int val) {
            if (index < root.l || root.r < index) return; // out of range.
            update(root, index, val);
        }
        
        /**
         * Returns the sum of the elements of nums between indices left and right inclusive 
         * (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
         */
        public int sumRange(int left, int right) {
            return sumRange(root, left, right);
        }

        private int sumRange(TreeNode node, int l, int r) {
            if (r < node.l || node.r < l) return 0; // 不相交
            if (l <= node.l && node.r <= r) return node.sum; // 完全相交
            // assert node.left != null && node.right != null : "叶子节点不可能突破'完全相交'的条件封锁，可以断言左右节点不为空";
            return sumRange(node.left, l, r) + sumRange(node.right, l, r); // 部分相交，交给各自子节点判断覆盖情况
        }
    
        private void update(TreeNode node, int index, int val) {
            if (node.isLeafNode()) {
                node.sum = val;
                return;
            }
    
            if (index <= node.left.r) {
                update(node.left, index, val);
            } else {
                update(node.right, index, val);
            }
    
            node.sum = node.left.sum + node.right.sum;
        }
    }
}
