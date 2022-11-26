package per.eicho.demo.leetcode_cn.offer;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>剑指 Offer 07. 重建二叉树 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/">
 *   剑指 Offer 07. 重建二叉树</a>
 */
public final class Offer07 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 0 <= 节点个数 <= 5000
        if (preorder == null || preorder.length == 0) return null;
        final int n = preorder.length;
        return buildTree(preorder, inorder, new int[]{0, n}, new int[]{0, n});
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, 
        int[] preorderRange, int[] inorderRange) {
        final int pl = preorderRange[0];
        final int pr = preorderRange[1];
        final int il = inorderRange[0];
        final int ir = inorderRange[1];
        
        final int rootVal = preorder[pl];
        final TreeNode root = new TreeNode(rootVal);
        // 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
        
        int p = il;
        while (inorder[p] != rootVal) p++;

        final int leftLen = p - il;
        if (leftLen > 0) {
            root.left = buildTree(preorder, inorder, 
                new int[]{pl + 1, pl + 1 + leftLen}, 
                new int[]{il, il + leftLen});
        }

        final int rightLen = ir - (p + 1);
        if (rightLen > 0) {
            root.right = buildTree(preorder, inorder, 
                new int[]{pl + 1 + leftLen, pr}, 
                new int[]{p + 1, ir});
        }
        return root;
    }
}
