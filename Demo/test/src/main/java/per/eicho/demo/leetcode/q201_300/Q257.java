package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>257. Binary Tree Paths 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-paths/">
 *   257. Binary Tree Paths</a>
 */
public final class Q257 {
    private List<String> result;
    private StringBuilder sb;

    public List<String> binaryTreePaths(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 100].
        // 2. -100 <= Node.val <= 100        
        result = new ArrayList<>();
        if (root == null) return result;
        sb = new StringBuilder();
        walkThrough(root);
        return result;
    }

    private void walkThrough(TreeNode root) {
        int appendedLen = sb.length();
        sb.append(root.val);
        appendedLen = sb.length() - appendedLen;

        if (isLeaf(root)) {
            // leaf node.
            result.add(sb.toString());
            deleteLastNChar(appendedLen);
            return;
        }

        sb.append("->");
        appendedLen += 2;

        if (root.left != null) walkThrough(root.left);
        if (root.right != null) walkThrough(root.right);
        deleteLastNChar(appendedLen);
    }

    private void deleteLastNChar(int appendedLen) {
        sb.delete(sb.length() - appendedLen, sb.length());
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
