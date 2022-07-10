package per.eicho.demo.leetcode.q1101_1200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1145. Binary Tree Coloring Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-coloring-game/">
 *   1145. Binary Tree Coloring Game</a>
 */
public final class Q1145 {

    int[] counts;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        // 1. The number of nodes in the tree is n.
        // 2. 1 <= x <= n <= 100
        // 3. n is odd.
        // 4. 1 <= Node.val <= n
        // 5. All the values of the tree are unique.
        counts = new int[]{0, 0, 0}; // l, r, parent
        count(root, x, false);

        final int total = counts[0] + counts[1] + counts[2] + 1;
        for (int i = 0; i <= 2; i++) {
            if (counts[i] > total - counts[i]) return true;
        }
        return false;
    }

    private int count(TreeNode node, int x, boolean found) {
        if (node == null) return 0;

        if (found) {
            // already found.
            return 1 + count(node.left, x, true) + count(node.right, x, true);
        } 
        
        // not found.
        if (node.val == x) {
            counts[0] = count(node.left, x, true);
            counts[1] = count(node.right, x, true);
            return 0;
        }
        
        counts[2]++;
        count(node.left, x, false);
        count(node.right, x, false);
        return 0;
    }
}
