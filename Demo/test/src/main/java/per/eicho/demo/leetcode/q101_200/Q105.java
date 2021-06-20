package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>105. Construct Binary Tree from Preorder and Inorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">
 *   105. Construct Binary Tree from Preorder and Inorder Traversal</a>
 */
final public class Q105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // preorder : root, left, right
        // inorder  : left, root, right
        assert 1 <= preorder.length && preorder.length <= 3000;
        assert preorder.length == inorder.length;
        //assert each element in [-3000, 3000];
        // preorder and inorder consist of unique values.
        // Each value of inorder also appears in preorder.
        // preorder is guaranteed to be the preorder traversal of the tree.
        // inorder is guaranteed to be the inorder traversal of the tree.

        return buildTree(
            preorder, 0, preorder.length - 1, 
            inorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTree(
        final int[] preorder, final int pl, final int pr, 
        final int[] inorder, final int il, final int ir) {
        final int rootVal = preorder[pl];
        final TreeNode root = new TreeNode(rootVal);
        
        // 1. leaf node check  
        if (pl == pr) {
            return root; // left: null, right: null
        }
        
        // 1. find root
        int iIndex = ir;
        while (inorder[iIndex] != rootVal) {
            iIndex--;
        }
        assert inorder[iIndex] == rootVal;
        final int leftCount = iIndex - il;
        final int rightCount = ir - iIndex;

        // 2. build left tree
        if (leftCount != 0) {
            root.left = buildTree(
                preorder, pl + 1, pl + leftCount, 
                inorder, il, il + leftCount - 1);
        }

        // 3. build right tree
        if (rightCount != 0) {
            root.right = buildTree(
                preorder, pr - rightCount + 1, pr, 
                inorder, ir - rightCount + 1, ir);
        }

        return root;
    }

}
