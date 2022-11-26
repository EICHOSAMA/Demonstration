package per.eicho.demo.leetcode_cn.offer;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>剑指 Offer 26. 树的子结构 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/">
 *   剑指 Offer 26. 树的子结构</a>
 */
public final class Offer26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 0 <= 节点个数 <= 10000
        // 约定空树不是任意一个树的子结构
        if (A == null) return false;
        if (B == null) return false;
        if (isContained(A, B)) return true;
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isContained(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        if (A.val != B.val) return false;
        if (!isContained(A.left, B.left)) return false;
        if (!isContained(A.right, B.right)) return false;
        return true;
    }
}
