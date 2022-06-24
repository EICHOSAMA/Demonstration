package per.eicho.demo.leetcode.q901_1000;

import java.util.HashMap;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>979. Distribute Coins in Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/distribute-coins-in-binary-tree/">
 *   979. Distribute Coins in Binary Tree</a>
 */
public final class Q979 {

    Map<TreeNode, Integer> needsInfo = new HashMap<>();

    public int distributeCoins(TreeNode root) {
        // 1. The number of nodes in the tree is n.
        // 2. 1 <= n <= 100
        // 3. 0 <= Node.val <= n
        // 4. The sum of all Node.val is n.
        countNeeds(root);
        return countMove(root);
    }

    private int countMove(TreeNode node) {
        if (node == null) return 0;

        int count = 0;
        if (node.left != null) {
            final int lNeeds = needsInfo.get(node.left);
            count += Math.abs(lNeeds);
            node.left.val -= lNeeds;
            count += countMove(node.left);
        }

        if (node.right != null) {
            final int rNeeds = needsInfo.get(node.right);
            count += Math.abs(rNeeds);
            node.right.val -= rNeeds;
            count += countMove(node.right);
        }

        node.val = 1;

        return count;
    }

    private int countNeeds(TreeNode node) {
        if (node == null) return 0;
        int needs = node.val - 1; // coins count - node count
        needs += countNeeds(node.left);
        needs += countNeeds(node.right);
        needsInfo.put(node, needs); 
        return needs;
    }

    public static void main(String[] args) {
        Q979 q979 = new Q979();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(0), new TreeNode(0));
        root.right = new TreeNode(3, new TreeNode(0), null);
        System.out.println(q979.distributeCoins(root));
    }
}
