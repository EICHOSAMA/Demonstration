package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>106. Construct Binary Tree from Inorder and Postorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/">
 *   106. Construct Binary Tree from Inorder and Postorder Traversal</a>
 */
final public class Q106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // inorder   : left, root, right
        // postorder : left, right, root
        assert 1 <= inorder.length && inorder.length <= 3000;
        assert inorder.length == postorder.length;
        //assert each element in [-3000, 3000];
        // inorder and postorder consist of unique values.
        // Each value of postorder also appears in inorder.
        // inorder is guaranteed to be the inorder traversal of the tree.
        // postorder is guaranteed to be the postorder traversal of the tree.

        return buildTree(
            inorder, 0, inorder.length - 1, 
            postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(
        final int[] inorder, final int il, final int ir, 
        final int[] postorder, final int pl, final int pr) {
        final int rootVal = postorder[pr];
        final TreeNode root = new TreeNode(rootVal);
        
        // 1. leaf node check  
        if (pl == pr) {
            return root; // left: null, right: null
        }
        
        // 1. find root
        int iIndex = il;
        while (inorder[iIndex] != rootVal) {
            iIndex++;
        }
        assert inorder[iIndex] == rootVal;
        final int leftCount = iIndex - il;
        final int rightCount = ir - iIndex;

        // 2. build left tree
        if (leftCount != 0) {
            root.left = buildTree(
                inorder, il, il + leftCount - 1, 
                postorder, pl, pl + leftCount - 1);
        }

        // 3. build right tree
        if (rightCount != 0) {
            root.right = buildTree(
                inorder, ir - rightCount + 1, ir, 
                postorder, pr - rightCount, pr - 1);
        }

        return root;
    }
}
