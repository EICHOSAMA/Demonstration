package per.eicho.demo.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.TreeNode;
import per.eicho.demo.leetcode.q001_100.Q94;
import per.eicho.demo.leetcode.q101_200.Q102;
import per.eicho.demo.leetcode.q101_200.Q144;
import per.eicho.demo.leetcode.q101_200.Q145;

/**
 * <p>二叉树遍历</p>
 * 
 * <pre>
 *   树的遍历，也被称为树的搜索，是图的遍历的一种。指的是按照某种规则，不重复地访问某种树的所有节点的过程。
 *   具体的访问操作可能是检查节点的值、更新节点的值等。不同的遍历方式，其访问节点的顺序是不一样的。
 *  
 *   1. 深度优先遍历
 *      a. 前序遍历 (Pre-Order Traversal)
 *      b. 中序遍历 (In-Order Traversal)
 *      c. 后续遍历 (Post-Order Traversal)
 *   2. 广度优先遍历
 *      a. 层级遍历 (Level-Order Traversal)
 * </pre>
 */
public final class BinaryTreeTraversalSample {
    
    /**
     * <p>前序遍历</p>
     * 
     * <pre>
     *  指先访问根，然后访问左右子树的遍历方式
     * </pre>
     * 
     * @see {@link Q144 144. Binary Tree Preorder Traversal}
     */
    public void preorederTraversal(TreeNode root) {
        if (root == null) {
            System.out.println(root);
            return;
        }

        System.out.println(root.val);
        preorederTraversal(root.left);
        preorederTraversal(root.right);
    }

    /**
     * <p>中序遍历</p>
     * 
     * <pre>
     *  指先访问左（右）子树，然后访问根，最后访问右（左）子树的遍历方式
     * </pre>
     * 
     * @see {@link Q94 Q94. Binary Tree Inorder Traversal}
     */    
    public void inorderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println(root);
            return;
        }

        inorderTraversal(root.left);
        System.out.println(root.val);
        inorderTraversal(root.right);
    }

    /**
     * <p>后序遍历</p>
     * 
     * <pre>
     *  指先访问左右子树，然后访问根的遍历方式
     * </pre>
     * 
     * @see {@link Q145 Q145. Binary Tree Postorder Traversal}
     */     
    public void postorderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println(root);
            return;
        }

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.println(root.val);
    }

    /**
     * <p>广度优先遍历</p>
     * 
     * <pre>
     *  和深度优先遍历不同，广度优先遍历会先访问离根节点最近的节点。
     *  二叉树的广度优先遍历又称按层次遍历。算法借助队列实现。
     * </pre>
     * 
     * @see {@link Q102 Q102. Binary Tree Level Order Traversal}
     */     
    public void levelOrderTraversal(TreeNode root) {
        if (root == null) {
            System.out.println(root);
            return;
        }

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();

            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                System.out.println(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
    }

}
