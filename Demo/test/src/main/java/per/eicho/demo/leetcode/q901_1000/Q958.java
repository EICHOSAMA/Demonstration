package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>958. Check Completeness of a Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-completeness-of-a-binary-tree/">958. Check Completeness of a Binary Tree</a>
 */
public final class Q958 {
    public boolean isCompleteTree(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 100].
        // 2. 1 <= Node.val <= 1000        
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int layerMaxSize = 1;
        while (!queue.isEmpty()) {
            System.out.println("Size:" + layerMaxSize);
            boolean nullMark = false;
            for (int i = 0; i < layerMaxSize; i++) {
                if (queue.isEmpty()) break;
                final TreeNode node = queue.poll();
                System.out.println(node != null ? node.val : "null");
                if (nullMark) {
                    if (node != null) return false;
                    continue;
                }

                if (node == null) {
                    nullMark = true;
                    continue;
                }

                // node != null
                queue.add(node.left);
                queue.add(node.right);
            }

            if (nullMark) {
                while (!queue.isEmpty()) {
                    if (queue.poll() != null) return false;
                }
            } 

            layerMaxSize *= 2;
        }

        return true;
    }
}
