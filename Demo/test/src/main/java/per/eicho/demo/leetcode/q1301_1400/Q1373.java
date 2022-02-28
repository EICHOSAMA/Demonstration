package per.eicho.demo.leetcode.q1301_1400;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1373. Maximum Sum BST in Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/">1373. Maximum Sum BST in Binary Tree</a>
 */
public final class Q1373 {

    private static final int SUM = 0;
    private static final int MIN = 1;
    private static final int MAX = 2;
    private static final int NOT_BST = Integer.MIN_VALUE;
    private static final int[] NOT_BST_RETURN = new int[]{NOT_BST, NOT_BST, NOT_BST};
    
    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 4 * 10^4].
        // 2. -4 * 10^4 <= Node.val <= 4 * 10^4        
        getInfo(root);
        return maxSum;
    }

    private int[] getInfo(TreeNode node) {
        if (node == null) return null;

        int[] l = getInfo(node.left);
        int[] r = getInfo(node.right);

        // Check sub tree
        if (l != null && l[SUM] == NOT_BST) return NOT_BST_RETURN;
        if (r != null && r[SUM] == NOT_BST) return NOT_BST_RETURN;
        if (l != null && node.val <= l[MAX]) return NOT_BST_RETURN;
        if (r != null && node.val >= r[MIN]) return NOT_BST_RETURN; 

        int[] result = new int[3];
        result[SUM] = node.val +(l != null ? l[SUM] : 0) + (r != null ? r[SUM] : 0); 
        result[MIN] = l != null ? l[MIN] : node.val;
        result[MAX] = r != null ? r[MAX] : node.val;
        maxSum = Math.max(maxSum, result[SUM]);
        return result;
    }
}
