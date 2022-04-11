package per.eicho.demo.datastructure.tree.binarytree.segmenttree;

/**
 * <p>数据结构 - 线段树</p>
 * 
 * <pre>
 *  线段树别称区间树, 英：segment tree (OR interval tree), 是相对高级的树形数据结构,
 *  用于解决“区间”类问题。如 <a href="https://leetcode.com/problems/range-sum-query-mutable/">动态区间求和 (LeetCode 307. Range Sum Query - Mutable)</a>。
 *  不变的数组可以使用sum数组可以在O(1)时间求得任意区间的和，但是面对动态的数组（有值变动），维护sum数组效率低成本高。
 *  因为此类问题可由线段树解决。
 *  
 *  相关复杂度：
 *   单点修改 → O(logN)
 *   区间修改 → O(logN)
 *   区间查询 → O(logN)
 *   （区间和，区间最大值，区间最小值）
 * </pre>
 * 
 * @see <a href="https://oi-wiki.org/ds/seg/">线段树 - OI Wiki</a>
 */
public final class SegementTree {
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

    SegementTree(int[] nums) {
        root = TreeNode.construceSegmentTree(nums);
    }

    public void update(int index, int toValue) {
        if (index < root.l || root.r < index) return; // out of range.
        update(root, index, toValue);
    }

    public int query(int l, int r) {
        return query(root, l, r);
    }

    private int query(TreeNode node, int l, int r) {
        if (r < node.l || node.r < l) return 0; // 不相交
        if (l <= node.l && node.r <= r) return node.sum; // 完全相交
        // assert node.left != null && node.right != null : "叶子节点不可能突破'完全相交'的条件封锁，可以断言左右节点不为空";
        return query(node.left, l, r) + query(node.right, l, r); // 部分相交，交给各自子节点判断覆盖情况
    }

    private void update(TreeNode node, int index, int toValue) {
        if (node.isLeafNode()) {
            node.sum = toValue;
            return;
        }

        if (index <= node.left.r) {
            update(node.left, index, toValue);
        } else {
            update(node.right, index, toValue);
        }

        node.sum = node.left.sum + node.right.sum;
    }
}
