package per.eicho.demo.leetcode.q101_200;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>173. Binary Search Tree Iterator 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-search-tree-iterator/">173. Binary Search Tree Iterator</a>
 */
@SuppressWarnings("unused")
public final class Q173 {
    private final static class BSTIterator {
        final Stack<TreeNode> stack;
        
        /**
         * Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. 
         * The pointer should be initialized to a non-existent number smaller than any element in the BST.
         * @param root
         */
        public BSTIterator(TreeNode root) {
            TreeNode pointer = new TreeNode(Integer.MIN_VALUE, null, root);
            stack = new Stack<>();
            
            addRight(pointer);
        }
        
        /**
         * Moves the pointer to the right, then returns the number at the pointer.
         */
        public int next() {
            return addRight(stack.pop());
        }

        private int addRight(TreeNode node) {
            final int val = node.val;
            
            node = node.right;
            while (node != null) {
                stack.add(node);
                node = node.left;
            }

            return val;
        }
        
        /**
         * Returns true if there exists a number in the traversal to the right of the pointer, 
         * otherwise returns false.
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
