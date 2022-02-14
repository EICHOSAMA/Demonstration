package per.eicho.demo.leetcode.q101_200;

import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>101. Symmetric Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/symmetric-tree/">101. Symmetric Tree</a>
 */
public final class Q101 {
    public boolean isSymmetric(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 1000].
        // 2. -100 <= Node.val <= 100        
        if (root == null) return true;

        List<TreeNode> li = new LinkedList<>();
        li.add(root.left);
        li.add(root.right);

        while (li.size() != 0) {
            TreeNode obj1  = li.remove(0);
            TreeNode obj2 = li.remove(0);

            if (obj1 == null && obj2 == null)
                continue;

            if (obj1 == null || obj2 == null || obj1.val != obj2.val)
                return false;

            li.add(obj1.right);
            li.add(obj2.left);
            li.add(obj1.left);
            li.add(obj2.right);
        }

        return true;
    }
}
