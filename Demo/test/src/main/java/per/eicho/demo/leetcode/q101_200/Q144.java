package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>144. Binary Tree Preorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/">144. Binary Tree Preorder Traversal</a>
 */
public final class Q144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 100].
        // 2. -100 <= Node.val <= 100        
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) return result;

        TreeNode p1 = root, p2 = null;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                // 找到左子树中最右的节点
                while (p2.right != null && p2.right != p1) p2 = p2.right;

                if (p2.right == null) {
                    // 如果是第一次那么建立到子树父亲节点的连接(p2.right = p1)。
					result.add(p1.val); // 前序遍历：输出父节点
                    p2.right = p1; // 建立连接，方便遍历时传送回父节点。
                    p1 = p1.left; // 前序遍历：处理左子树
                    continue;
                } else {
                    // 如果是第二次那么断开到父亲节点的连接(p2.right = null)
                    p2.right = null;
                }
            } else {
				result.add(p1.val);
            }
            p1 = p1.right;
        }
		return result;
    }
}
