package per.eicho.demo.leetcode_cn.offer;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>剑指 Offer 54. 二叉搜索树的第k大节点 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/">
 *   剑指 Offer 54. 二叉搜索树的第k大节点</a>
 */
public final class Offer54 {
    public int kthLargest(TreeNode root, int k) {
        // 1 ≤ k ≤ 二叉搜索树元素个数
        return search(root, new int[]{k}).val;
    }

    private TreeNode search(TreeNode root, int[] k) {
        if (root == null) return null;

        final TreeNode r = root.right;
        TreeNode trySearchRight = search(r, k);
        if (trySearchRight != null) return trySearchRight;
        if (k[0] == 1) return root;
        k[0]--;
        return search(root.left, k);
    }
}
