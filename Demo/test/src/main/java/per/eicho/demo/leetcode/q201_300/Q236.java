package per.eicho.demo.leetcode.q201_300;

import java.util.HashSet;
import java.util.Set;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>236. Lowest Common Ancestor of a Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/">236. Lowest Common Ancestor of a Binary Tree</a>
 */
public final class Q236 {

    TreeNode p;
    TreeNode q;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. The number of nodes in the tree is in the range [2, 105].
        // 2. -10^9 <= Node.val <= 10^9
        // 3. All Node.val are unique.
        // 4. p != q
        // 5. p and q will exist in the tree.

        this.p = p;
        this.q = q;

        return postOrderTraversal(root, new HashSet<>());
    }

    private TreeNode postOrderTraversal(TreeNode node, Set<TreeNode> set) {
        if (node == null) return null;

        Set<TreeNode> setL = new HashSet<>();
        TreeNode left = postOrderTraversal(node.left, setL);
        if (left != null) return left;

        Set<TreeNode> setR = new HashSet<>();
        TreeNode right = postOrderTraversal(node.right, setR);
        if (right != null) return right;

        set.addAll(setL);
        set.addAll(setR);
        set.add(node);

        if (set.contains(p) && set.contains(q)) return node;
        return null;
    }
}
