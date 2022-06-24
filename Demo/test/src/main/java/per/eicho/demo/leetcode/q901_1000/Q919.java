package per.eicho.demo.leetcode.q901_1000;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>919. Complete Binary Tree Inserter 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/complete-binary-tree-inserter/">
 *   919. Complete Binary Tree Inserter/a>
 */
@SuppressWarnings("unused")
public final class Q919 {
    private static final class CBTInserter {

        final List<TreeNode> cbt;

        /** Initializes the data structure with the root of the complete binary tree. */
        public CBTInserter(TreeNode root) {
            // 1. The number of nodes in the tree will be in the range [1, 1000].
            // 2. 0 <= Node.val <= 5000
            // 3. root is a complete binary tree.
            // 4. 0 <= val <= 5000
            cbt = new ArrayList<>();
            cbt.add(root);
            int p = 0;
            while (p < cbt.size()) {
                final TreeNode node = cbt.get(p++);
                if (node.left == null) break;
                cbt.add(node.left);
                if (node.right == null) break;
                cbt.add(node.right);
            }
        }
        
        /**
         * Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete, 
         * and returns the value of the parent of the inserted TreeNode.
         */
        public int insert(int val) {
            // 5. At most 10^4 calls will be made to insert and get_root.
            final TreeNode node = new TreeNode(val);
            final int idx = cbt.size();
            cbt.add(node);

            final int parentIdx = (idx + 1) / 2;
            final TreeNode parent = cbt.get(parentIdx);
            if (idx % 2 == 1) {
                parent.left = node;
            } else {
                parent.right = node;
            }

            return parent.val;
        }
        
        /** Returns the root node of the tree. */
        public TreeNode get_root() {
            // 5. At most 10^4 calls will be made to insert and get_root.
            return cbt.get(0);
        }
    }
    
}
