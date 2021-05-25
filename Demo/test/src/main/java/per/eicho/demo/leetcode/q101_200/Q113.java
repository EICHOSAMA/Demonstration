package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.RootPaneContainer;

import org.graalvm.compiler.hotspot.nodes.aot.ResolveDynamicConstantNode;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>113. Path Sum II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/path-sum-ii/">113. Path Sum II</a>
 */
final public class Q113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        final List<List<Integer>> result = new ArrayList<>();
        search(result, new Stack<>(), root, targetSum);
        return result;
    }

    private void search(final List<List<Integer>> result, final Stack<Integer> workspace,TreeNode node, int targetSum) {
        if (null == node) {
            return;
        }

        workspace.add(node.val);
        targetSum -= node.val;
        if (node.left != null || node.right != null) {
            search(result, workspace, node.left, targetSum);
            search(result, workspace, node.right, targetSum);
        } else {
            if (0 == targetSum) {
                // save
                result.add(new ArrayList<>(workspace));
            }
        }
        workspace.pop();
    }
}
