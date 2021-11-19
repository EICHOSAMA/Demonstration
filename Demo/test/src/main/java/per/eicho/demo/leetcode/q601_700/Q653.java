package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>653. Two Sum IV - Input is a BST 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/two-sum-iv-input-is-a-bst/">653. Two Sum IV - Input is a BST</a>
 */
public final class Q653 {
    public boolean findTarget(TreeNode root, int k) {
        final List<Integer> ascList = bst2ASCList(root);

        for (int i = 0; i < ascList.size() - 1; i++) {
            final int a = ascList.get(i);
            final int b = k - a;
            if (b >= a) {
                if (binarySearch(ascList, b, i + 1, ascList.size() - 1)) return true;
            }
        }

        return false;
    }

    private boolean binarySearch(final List<Integer> ascList, final int target, int l, int r) {
        // [l, r]
        if (l > r) return false;
        if (l == r) return ascList.get(l) == target;

        final int mid = (l + r + 1) / 2;
        final int num = ascList.get(mid);

        if (target < num) return binarySearch(ascList, target, l, mid - 1);
        return binarySearch(ascList, target, mid, r);
    }

    private List<Integer> bst2ASCList(TreeNode root) {
        final List<Integer> result = new ArrayList<>();

        if (root.left != null) result.addAll(bst2ASCList(root.left));
        result.add(root.val);
        if (root.right != null) result.addAll(bst2ASCList(root.right));
        return result;
    }
}
