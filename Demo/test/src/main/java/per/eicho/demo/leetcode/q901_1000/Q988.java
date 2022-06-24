package per.eicho.demo.leetcode.q901_1000;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>988. Smallest String Starting From Leaf 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-string-starting-from-leaf/">
 *   988. Smallest String Starting From Leaf</a>
 */
public final class Q988 {

    String result = null;
    int smallest;
    StringBuilder sb;

    public String smallestFromLeaf(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 8500].
        // 2. 0 <= Node.val <= 25
        smallest = findSmallestLeafNode(root);
        sb = new StringBuilder();
        search(root);
        return new StringBuilder(result).reverse().toString();
    }

    private int compare(String s1, String s2) {
        int p1 = s1.length() - 1;
        int p2 = s2.length() - 1;

        while (p1 >= 0 && p2 >= 0 && s1.charAt(p1) == s2.charAt(p2)) {
            p1--; p2--;   
        }

        if (p1 < 0) return -1;
        if (p2 < 0) return 1;
        return Character.compare(s1.charAt(p1), s2.charAt(p2));
    }

    private void search(TreeNode node) {
        
        if (node.left == null && node.right == null) {
            if (node.val != smallest) return;
            sb.append((char)(node.val + 'a'));
            final String candidate = sb.toString();
            result = result == null ? candidate : (compare(result, candidate) < 0 ? result : candidate);
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        if (node.left != null) {
            sb.append((char)(node.val + 'a'));
            search(node.left);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (node.right != null) {
            sb.append((char)(node.val + 'a'));
            search(node.right);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private int findSmallestLeafNode(TreeNode node) {
        if (node == null) return 26;
        if (node.left == null && node.right == null) return node.val;
        return Math.min(findSmallestLeafNode(node.left), findSmallestLeafNode(node.right));
    }
}
