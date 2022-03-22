package per.eicho.demo.leetcode.explore.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import per.eicho.demo.algorithm.tree.BinaryTreeTraversalSample;
import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>二分树的遍历</p>
 * 
 * <pre>
 *  二分树的递归遍历非常之简单，是基础中的基础，而用栈模拟递归则相对较难。
 *  属于Medium - Hard之间的难度。本类里的前中后序遍历都使用栈模拟的方式来解决。
 * </pre>
 * 
 * @see {@link BinaryTreeTraversalSample 二分树的遍历(递归)}
 */
final class BinaryTreeTraversalUsingStack {

    /**
     * <p>栈模拟 - 树的前序遍历</p>
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        final List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        
        final Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            // FILO
            if (node.right != null) stack.add(node.right); 
            if (node.left != null) stack.add(node.left);
        }

        return result;
    }

    /**
     * <p>栈模拟 - 树的中序遍历</p>
     */    
    public List<Integer> inorderTraversal(TreeNode root) {
        final List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        
        final Stack<TreeNode> stack = new Stack<>();

        TreeNode cursor = root;
        while (cursor != null) {
            stack.add(cursor);
            cursor = cursor.left;
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            cursor = node.right;
            while (cursor != null) {
                stack.add(cursor);
                cursor = cursor.left;
            }
        }

        return result;
    }

    /**
     * <p>栈模拟 - 树的后序遍历</p>
     */      
    public List<Integer> postorderTraversal(TreeNode root) {
        final List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        
        final Stack<Object> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            final Object obj = stack.pop();
            // 通过类型信息知晓某节点是否被访问过。
            // 被访问并扩展过的根节点会在栈中从TreeNode变为Integer类型
            if (obj instanceof Integer) {
                result.add((Integer)obj);
                continue;
            }

            final TreeNode node = (TreeNode)obj;
            stack.add(node.val);
            // FILO
            if (node.right != null) stack.add(node.right); 
            if (node.left != null) stack.add(node.left);
        }

        return result;
    }

    /**
     * <p>广度优先遍历</p>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        final List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            final int count = queue.size();
            final List<Integer> layer = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                final TreeNode node = queue.poll();
                layer.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right); 
            }
            result.add(layer);
        }
        return result;
    }
}
