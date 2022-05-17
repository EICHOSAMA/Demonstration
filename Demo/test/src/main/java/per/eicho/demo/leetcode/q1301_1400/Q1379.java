package per.eicho.demo.leetcode.q1301_1400;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/">
 *   1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree</a>
 */
public final class Q1379 {

    public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. The values of the nodes of the tree are unique.
        // 3. target node is a node from the original tree and is not null.             
        if (original == null) return null;
        if (original == target) return cloned;
        TreeNode result = getTargetCopy2(original.left, cloned.left, target);
        if (result != null) return result;
        return getTargetCopy2(original.right, cloned.right, target);
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // [NOTICE] : LeetCode BUG cause this MorrisTraversal Solution Get TLE error
        //            PLEASE FIX IT. (RUNNING PERFECTLY AT LOCAL ENV.)
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. The values of the nodes of the tree are unique.
        // 3. target node is a node from the original tree and is not null.
        TreeNode cursor = original;
        TreeNode cursorClone = cloned;
        while (cursor != target) {
            if (cursor.left != null) {
                TreeNode helperP = cursor.left;
                TreeNode helperPClone = cursorClone.left;
                while (helperP.right != null && helperP.right != cursor) {
                    helperP = helperP.right;
                    helperPClone = helperPClone.right;
                }

                if (helperP.right != null) {              
                    // already been visited. (2rd time)
                    cursor = helperP.right.right;
                    helperP.right = null;

                    cursorClone = helperPClone.right.right;
                    helperPClone.right = null;
                } else {                    
                    // first time been visited.
                    helperP.right = cursor;
                    cursor = cursor.left;

                    helperPClone.right = cursorClone;
                    cursorClone = cursorClone.left;
                }
            } else {        
                cursor = cursor.right;
                cursorClone = cursorClone.right;
            }   
        }
        return cursorClone;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Q1379 q1379 = new Q1379();
        TreeNode root1 = new TreeNode(1);
        TreeNode l1 = root1.left = new TreeNode(2);
        TreeNode r1 = root1.right = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        TreeNode l2 = l1.left = new TreeNode(4, new TreeNode(8), new TreeNode(9));
        TreeNode l3 = l1.right = new TreeNode(5, new TreeNode(10), null);
        
        TreeNode root2 = new TreeNode(1);
        TreeNode l12 = root2.left = new TreeNode(2);
        TreeNode r12 = root2.right = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        TreeNode l22 = l12.left = new TreeNode(4, new TreeNode(8), new TreeNode(9));
        TreeNode l32 = l12.right = new TreeNode(5, new TreeNode(10), null);
        

        System.out.println(q1379.getTargetCopy(root1, root2, l3) == l32);
        
    }
}
