package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>637. Average of Levels in Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/average-of-levels-in-binary-tree/">637. Average of Levels in Binary Tree</a>
 */
public final class Q637 {
    public List<Double> averageOfLevels(TreeNode root) {
        final List<Double> result = new ArrayList<>();

        // 1. prepare work space (to store temp data)
        final List<Queue<TreeNode>> ws = new ArrayList<>(2);
        ws.add(new LinkedList<>());
        ws.add(new LinkedList<>());
        
        // 2. init
        int layer = 0;
        ws.get(layer).add(root);

        // 3. process
        while (ws.get(layer % 2).size() != 0) {
            final Queue<TreeNode> currentLayer = ws.get(layer % 2);
            final Queue<TreeNode> nextLayer = ws.get((layer + 1) % 2);

            final int count = currentLayer.size();
            
            double sum = 0;
            while (currentLayer.peek() != null) {
                final TreeNode node = currentLayer.poll();

                if (node.left != null) nextLayer.add(node.left);
                if (node.right != null) nextLayer.add(node.right);

                sum += node.val;
            }

            result.add(sum / count);
            layer++;
        }
        return result;
    }
}
