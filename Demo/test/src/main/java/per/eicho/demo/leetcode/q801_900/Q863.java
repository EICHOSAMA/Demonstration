package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>863. All Nodes Distance K in Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/transpose-matrix/">
 *   863. All Nodes Distance K in Binary Tree</a>
 */
public final class Q863 {

    List<Integer> result;
    private static final int DISTANCE_UNKNOWN = -1;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 1. The number of nodes in the tree is in the range [1, 500].
        // 2. 0 <= Node.val <= 500
        // 3. All the values Node.val are unique.
        // 4. target is the value of one of the nodes in the tree.
        // 5. 0 <= k <= 1000
        result = new ArrayList<>();
        if (k == 0) return Arrays.asList(target.val);
        search(root, DISTANCE_UNKNOWN, target.val, k);
        return result;
    }

    private int search(TreeNode node, int distance, final int target, final int k) {
        if (node == null) return DISTANCE_UNKNOWN;
        
        if (distance == DISTANCE_UNKNOWN) {
            if (node.val == target) {
                // 1 <= k <= 1000
                search(node.left, 1, target, k);
                search(node.right, 1, target, k);
                return 0;
            }
            
            final int lDis = search(node.left, DISTANCE_UNKNOWN, target, k);
            if (lDis != DISTANCE_UNKNOWN) {
                // find target at left sub tree.
                distance = lDis + 1;
                if (distance == k) {
                    result.add(node.val);
                } else if (distance < k) {
                    search(node.right, distance + 1, target, k);
                }
                return distance;
            }

            final int rDis = search(node.right, DISTANCE_UNKNOWN, target, k);
            if (rDis != DISTANCE_UNKNOWN) {
                // find target at right sub tree.
                distance = rDis + 1;
                if (distance == k) {
                    result.add(node.val);
                } else if (distance < k) {
                    search(node.left, distance + 1, target, k);
                }
                return distance;
            }

            return DISTANCE_UNKNOWN;
        } else {
            if (distance == k) {
                result.add(node.val);
            } else {
                search(node.left, distance + 1, target, k);
                search(node.right, distance + 1, target, k);
            }
            return distance;
        }
    }
}
