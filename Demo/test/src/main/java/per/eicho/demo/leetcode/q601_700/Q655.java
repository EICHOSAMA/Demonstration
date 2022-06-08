package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>655. Print Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/print-binary-tree/">655. Print Binary Tree</a>
 */
public final class Q655 {
    public List<List<String>> printTree(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 2^10].
        // 2. -99 <= Node.val <= 99
        // 3. The depth of the tree will be in the range [1, 10].        
        final int h = getHeight(root);

        // The number of columns n should be equal to 2^height - 1.
        final int n = power(2, h) - 1;
        // The height of the tree is height and the number of rows m should be equal to height.
        final int m = h;

        final List<List<Object>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            final List<Object> row = new ArrayList<>(n);
            result.add(row);
            for (int j = 0; j < n; j++) { row.add(""); }
        }

        result.get(0).set(n / 2, root);

        for (int r = 0; r < m; r++) {
            final List<Object> row = result.get(r);
            for (int c = 0; c < n; c++) {
                if (row.get(c) instanceof String) continue;
                final TreeNode node = (TreeNode)row.get(c);
                row.set(c, "" + node.val);

                // For each node that has been placed in the matrix at position res[r][c], 
                final int offset = power(2, h - r - 2);
                // place its left child at res[r+1][c-2^(height-r-2)] 
                if (node.left != null) {
                    result.get(r + 1).set(c - offset, node.left);
                }

                // and its right child at res[r+1][c+2^(height-r-2)].
                if (node.right != null) {
                    result.get(r + 1).set(c + offset, node.right);
                }
            }
        }
        return casting(result);
    }

    private List<List<String>> casting(List<List<Object>> original) {
        final List<List<String>> result = new ArrayList<>(original.size());
        for (int i = 0; i < original.size(); i++) {
            final List<Object> oRow = original.get(i);
            final List<String> nRow = new ArrayList<>(oRow.size());
            for (int j = 0; j < oRow.size(); j++) {
                nRow.add((String)oRow.get(j));
            }
            result.add(nRow);
        }
        return result;
    }

    private int power(int a, int b) {
        if (b == 0) return 1;
        final int half = power(a, b / 2);
        return half * half * (b % 2 == 1 ? a : 1); 
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}
