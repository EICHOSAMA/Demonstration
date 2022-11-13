package per.eicho.demo.nowcoder.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import per.eicho.demo.nowcoder.datastructure.TreeNode;

public final class JZ032 {
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        final ArrayList<Integer> result = new ArrayList<>();
        final Queue<TreeNode> processQueue = new LinkedList<>();
        addIfNotNull(processQueue, root);
        while (!processQueue.isEmpty()) {
            for (int i = 0, size = processQueue.size(); i < size; i++) {
                final TreeNode node = processQueue.poll();
                result.add(node.val);
                addIfNotNull(processQueue, node.left);
                addIfNotNull(processQueue, node.right);
            }
        }

        return result;
    }

    private void addIfNotNull(Queue<TreeNode> queue, TreeNode node) {
        if (null == node) return;
        queue.add(node);
    }
}
