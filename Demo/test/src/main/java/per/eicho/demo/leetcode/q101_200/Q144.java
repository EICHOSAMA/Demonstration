package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>144. Binary Tree Preorder Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-preorder-traversal/">144. Binary Tree Preorder Traversal</a>
 */
public final class Q144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 100].
        // 2. -100 <= Node.val <= 100        
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>(); 
		stack.push(root);
		result.add(root.val);
		
		// record the recent popped node.
		TreeNode lastNode = null;  
		while (stack.isEmpty() == false) {
			TreeNode rt = stack.lastElement();
			TreeNode l = rt.left;
			TreeNode r = rt.right;
			
			/*
			 * case 1. rt is leaf node, pop.
			 * case 2. left child has been traversed and no right child exist, pop. nor see case 5.
			 * case 3. right child has been traversed , pop.
			 * case 4. left child has not been traversed, add to stack.
			 * case 5. right child has not been traversed, add to stack.
			 */
			
			// 1 : check whether node rt is a leaf node.
			if (l == null && r == null) {
				lastNode = rt;
				stack.pop();
				continue;
			}
			
			// 2 : check whether node rt's left node has been traversed completely.
			if (l != null && lastNode == l && null == r) {
				lastNode = rt;
				stack.pop();
				continue;
			}
			
			// 3 : check whether node rt's right node has been traversed completely.
			if (r != null && lastNode == r) {
				lastNode = rt;
				stack.pop();
				continue;
			}
			
			// 4 : traverse left node .
			if (null != l && l != lastNode) {
				lastNode = null;
				stack.push(l);
				result.add(l.val);
				continue;
			}

			// 5 : traverse right node .			
			if (null != r) {
				lastNode = null;
				stack.push(r);
				result.add(r.val);
				continue;
			}
		}
		return result;
    }
}
