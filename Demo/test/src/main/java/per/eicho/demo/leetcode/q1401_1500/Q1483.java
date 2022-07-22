package per.eicho.demo.leetcode.q1401_1500;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>1483. Kth Ancestor of a Tree Node 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-ancestor-of-a-tree-node/">
 *   1483. Kth Ancestor of a Tree Node</a>
 */
@SuppressWarnings("unused")
public final class Q1483 {
    private static final class TreeAncestor {

        private static final class TreeNode {
            final int val;
            TreeNode p;
            final List<TreeNode> children;
            TreeNode(int val) { 
                this.val = val; 
                this.children = new ArrayList<>();
            }

            static void connect(TreeNode parent, TreeNode child) {
                child.p = parent;
                parent.children.add(child);
            }
        }

        final TreeNode[] nodes;
        final List<List<TreeNode>> jumpInfoList;

        public TreeAncestor(int n, int[] parent) {
            // 1. 1 <= k <= n <= 5 * 104
            // 2. parent.length == n
            // 3. parent[0] == -1
            // 4. 0 <= parent[i] < n for all 0 < i < n
            nodes = new TreeNode[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new TreeNode(i);
            }

            // parent[0] == -1
            for (int i = 1; i < n; i++) {
                TreeNode.connect(nodes[parent[i]], nodes[i]);
            }

            jumpInfoList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                jumpInfoList.add(new ArrayList<>());
            }
            
            final List<TreeNode> workspace = new ArrayList<>();
            traverse(nodes[0], workspace);
        }

        private void traverse(TreeNode node, List<TreeNode> workspace) {
            if (node == null) return;
            final int idx = workspace.size();
            workspace.add(node);
            for (TreeNode child : node.children) traverse(child, workspace);
            workspace.remove(idx);

            final List<TreeNode> jumpInfo = jumpInfoList.get(node.val);
            final int bound = workspace.size();
            for (int i = bound, step = 1; i - step >= 0; step *= 2) {
                // 1, 2, 4, ...
                jumpInfo.add(workspace.get(i - step));
            }
        }
        
        public int getKthAncestor(int node, int k) {
            if (k == 0) return node;
            // 1. 1 <= k <= n <= 5 * 104
            // 2. 0 <= node < n
            // 3. There will be at most 5 * 10^4 queries.
            final List<TreeNode> jumpInfo = jumpInfoList.get(node);
            
            int p = 0; 
            final int size = jumpInfo.size();
            int step = 1;
            while (p + 1 < size && step * 2 <= k) {
                step *= 2;
                p++;
            }

            if (size == 0) return -1;
            final int jumpToNode = jumpInfo.get(p).val;
            return getKthAncestor(jumpToNode, k - step);
        }
    }
}
