package per.eicho.demo.leetcode.q401_500;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>449. Serialize and Deserialize BST 的题解代码 </p>
 * 
 * <pre>
 *  BST, binary search tree.
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/serialize-and-deserialize-bst/">449. Serialize and Deserialize BST</a>
 */
public final class Q449 {
    private static class Codec {

        /**
         * Encodes a tree to a single string.
         */
        public String serialize(TreeNode root) {
            // 1. The number of nodes in the tree is in the range [0, 10^4].
            // 2. 0 <= Node.val <= 10^4
            // 3. The input tree is guaranteed to be a binary search tree.            
            if (root == null) return "";
            final StringBuilder sb = new StringBuilder();

            final Stack<TreeNode> stack = new Stack<>();
            stack.add(root);

            while (!stack.isEmpty()) {
                final TreeNode node = stack.pop();
                if (node != null) {
                    stack.add(node.right);
                    stack.add(node.left); // process left first.
                    sb.append(node.val);
                } else {
                    sb.append('#');
                }

                if (!stack.isEmpty()) sb.append(',');
            }
            return sb.toString();
        }

        /**
         * Decodes your encoded data to tree.
         */
        public TreeNode deserialize(String data) {
            // 1. The number of nodes in the tree is in the range [0, 10^4].
            // 2. 0 <= Node.val <= 10^4
            // 3. The input tree is guaranteed to be a binary search tree.                
            if (data.length() == 0) return null; // ""

            final TreeNode vituralRoot = new TreeNode(-1, null, null);
            final Stack<TreeNode> stack = new Stack<>();
            final Stack<Boolean> stateStack = new Stack<>();
            stack.add(vituralRoot);
            stateStack.add(Boolean.FALSE);


            for (int i = 0; i < data.length(); i++) {
                final TreeNode top = stack.peek();
                final Boolean state = stateStack.peek();

                char ch = data.charAt(i);
                if (ch == '#') { // null
                    i++;
                    if (state == Boolean.TRUE) { // TRUE → POP (Process finished)
                        top.right = null;
                        stack.pop();
                        stateStack.pop();
                    } else { // FALSE → TRUE
                        top.left = null;
                        stateStack.pop();
                        stateStack.add(Boolean.TRUE);
                    }
                    continue;
                }

                int val = (ch - '0');
                while (++i < data.length() && (ch = data.charAt(i)) != ',') {
                    val *= 10;
                    val += (ch - '0');
                }
                
                if (state == Boolean.TRUE) {
                    top.right = new TreeNode(val);
                    stack.pop();
                    stateStack.pop();

                    stack.add(top.right);
                    stateStack.add(Boolean.FALSE);
                } else {
                    top.left = new TreeNode(val);
                    stack.add(top.left);
                    stateStack.pop();
                    stateStack.add(Boolean.TRUE);
                    stateStack.add(Boolean.FALSE);
                }
            }

            return vituralRoot.left;
        }
    }

    public static void main(String[] args) {
        Codec q449 = new Codec();

        TreeNode input = new TreeNode(1, null, new TreeNode(2));
        System.out.println(q449.serialize(input));
        System.out.println("-----");
        System.out.println(q449.serialize(q449.deserialize(q449.serialize(input))));
    }
}
