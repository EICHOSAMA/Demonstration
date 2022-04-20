package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>173. Binary Search Tree Iterator 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-search-tree-iterator/">173. Binary Search Tree Iterator</a>
 */
public final class Q173 {
    private final static class BSTIterator {

        TreeNode cursor;
        /**
         * Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. 
         * The pointer should be initialized to a non-existent number smaller than any element in the BST.
         * @param root
         */
        public BSTIterator(TreeNode root) {
            cursor = genConnection(root);
        }

        private TreeNode genConnection(TreeNode root) {
            if (root == null) return null;

            TreeNode p = root;
            TreeNode helperP;
            while (hasLeftSubTree(p)) {
                helperP = findRightMostNodeOfLeftSubTree(p);
                connect(helperP, p);
                p = p.left;
            }
            return p;
        }
        
        /**
         * Moves the pointer to the right, then returns the number at the pointer.
         */
        public int next() {
            final int result = cursor.val;
            move2Next();
            return result;
        }

        private void move2Next() {
            cursor = cursor.right;
            
            while (cursor != null) {
                // case1. has left sub tree.
                if (hasLeftSubTree(cursor)) {
                    TreeNode rmn = findRightMostNodeOfLeftSubTree(cursor);
    
                    if (!isConnected(rmn)) {
                        // case1.1 first time access the right most node.
                        // then connect right most node to root.
                        connect(rmn, cursor);
                        cursor = cursor.left;
                    } else {
                        // case1.2 second time access the right most node.
                        // which means the left sub tree has already been traversed.
                        // then disconnect.
                        disConnect(rmn);
                        return; // stop moving.
                    }
                } else {
                    return; // stop moving.
                }
            }
        }
        
        /**
         * Returns true if there exists a number in the traversal to the right of the pointer, 
         * otherwise returns false.
         */
        public boolean hasNext() {
            return cursor != null;
        }

        private boolean hasLeftSubTree(TreeNode node) {
            return node.left != null;
        }

        private TreeNode findRightMostNodeOfLeftSubTree(TreeNode root) {
            TreeNode cursor = root.left;
            while (cursor.right != null && cursor.right != root) {
                cursor = cursor.right;
            }
            return cursor;
        }

        private boolean isConnected(TreeNode rightMostNode) {
            return rightMostNode.right != null;
        }

        private void connect(TreeNode rightMostNode, TreeNode root) {
            rightMostNode.right = root;
        }

        private void disConnect(TreeNode rightMostNode) {
            rightMostNode.right = null;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator bIterator = new BSTIterator(root);

        System.out.println(bIterator.next()); // 3
        System.out.println(bIterator.next()); // 7
        System.out.println(bIterator.hasNext()); // true
        System.out.println(bIterator.next()); // 9
        System.out.println(bIterator.hasNext()); // true
        System.out.println(bIterator.next()); // 15
        System.out.println(bIterator.hasNext()); // true
        System.out.println(bIterator.next()); // 20
        System.out.println(bIterator.hasNext()); // false
    }
}
