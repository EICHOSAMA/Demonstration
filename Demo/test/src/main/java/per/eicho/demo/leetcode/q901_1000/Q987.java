package per.eicho.demo.leetcode.q901_1000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>987. Vertical Order Traversal of a Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/">
 *   987. Vertical Order Traversal of a Binary Tree</a>
 */
public final class Q987 {
    
    private static final class Info {
        final int val;
        final int row;

        Info(int val, int row) {
            this.val = val;
            this.row = row;
        }
    }

    int lMost = 0;
    int rMost = 0;
    // <col, List<Value>>
    Map<Integer, List<Info>> map;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 1000].
        // 2. 0 <= Node.val <= 1000        
        map = new HashMap<>();
        map.put(0, new ArrayList<>());
        search(root, 0, 0);
        final List<List<Integer>> result = new ArrayList<>(map.size());
        for (int i = lMost; i <= rMost; i++) {
            final List<Info> values = map.get(i);
            values.sort((i1, i2) -> {
                if (i1.row != i2.row) return Integer.compare(i1.row, i2.row);
                return Integer.compare(i1.val, i2.val);
            });
            result.add(values.stream().map(info -> info.val).collect(Collectors.toList()));
        }

        return result;
    }

    private void search(TreeNode node, int row, int col) {
        if (node == null) return;
        if (!map.containsKey(col)) {
            final List<Info> values = new ArrayList<>();
            lMost = Math.min(lMost, col);
            rMost = Math.max(rMost, col);
            map.put(col, values);
        }

        map.get(col).add(new Info(node.val, row));
        search(node.left, row + 1, col - 1);
        search(node.right, row + 1, col + 1);
    }
}
