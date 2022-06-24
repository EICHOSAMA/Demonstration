package per.eicho.demo.leetcode.q801_900;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>889. Construct Binary Tree from Preorder and Postorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/">
 *   889. Construct Binary Tree from Preorder and Postorder Traversal</a>
 */
public final class Q889 {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // 1. 1 <= preorder.length <= 30
        // 2. 1 <= preorder[i] <= preorder.length
        // 3. All the values of preorder are unique.
        // 4. postorder.length == preorder.length
        // 5. 1 <= postorder[i] <= postorder.length
        // 6. All the values of postorder are unique.
        // 7. It is guaranteed that preorder and postorder are the preorder traversal 
        //    and postorder traversal of the same binary tree.
        return constructFromPrePost(
            preorder, 0, preorder.length - 1, 
            postorder, 0, postorder.length - 1);
    }

    private TreeNode constructFromPrePost(
        int[] preorder, int l1, int r1, 
        int[] postorder, int l2, int r2) {
        final int val = preorder[l1];
        final TreeNode node = new TreeNode(val);
        if (l1 == r1) return node;
        
        final int lVal = preorder[l1 + 1];
        final int rVal = postorder[r2 - 1];
        if (lVal == rVal) {
            node.left = constructFromPrePost(preorder, l1 + 1, r1, postorder, l2, r2 - 1);
            node.right = null;
        } else {
            int p = l1;
            while (preorder[p] != rVal) p++;
            final int countL = p - l1 - 1;
            final int countR = r1 - p + 1;

            node.left = constructFromPrePost(preorder, l1 + 1, p - 1, postorder, l2, l2 + countL - 1);
            node.right = constructFromPrePost(preorder, p, r1, postorder, r2 - countR, r2 - 1);
        }

        return node;
    }
}
