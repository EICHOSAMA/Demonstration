package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>99. Recover Binary Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/recover-binary-search-tree/">99. Recover Binary Search Tree</a>
 */
public final class Q99 {
    public void recoverTree(TreeNode root) {
        final List<TreeNode> allNodes = tie(root);
        // i.e. 1324 vs 1234
        final List<TreeNode> sortedNodes = allNodes.stream()
            .sorted((nd1, nd2) -> Integer.compare(nd1.val, nd2.val))
            .collect(Collectors.toList());
        
        for (int i = 0; i < sortedNodes.size(); i++) {
            final TreeNode nd1 = allNodes.get(i);
            final TreeNode nd2 = sortedNodes.get(i);

            if (nd1.val != nd2.val) {
                int temp = nd2.val;
                nd2.val = nd1.val;
                nd1.val = temp;
                break;
            }
        }
    }

    private List<TreeNode> tie(TreeNode rNode) {
        final List<TreeNode> result = new ArrayList<>();
        doTie(rNode, result);
        return result;
    }

    private void doTie(TreeNode rNode, final List<TreeNode> result) {
        if (null == rNode) return;
        doTie(rNode.left, result);
        result.add(rNode);
        doTie(rNode.right, result);
    }
}
