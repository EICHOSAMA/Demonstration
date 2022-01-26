package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>133. Clone Graph 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/clone-graph/">133. Clone Graph</a>
 */
public final class Q133 {

    @SuppressWarnings("unused")
    private static final class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        // 1. The number of nodes in the graph is in the range [0, 100].
        // 2. 1 <= Node.val <= 100
        // 3. Node.val is unique for each node.
        // 4. There are no repeated edges and no self-loops in the graph.
        // 5. The Graph is connected and all nodes can be visited starting from the given node.
        if (node == null) return null;
   
        final Map<Node, Node> map = new HashMap<>();
        final Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (queue.size() != 0) {
            final Node processingNode = queue.poll();

            if (map.containsKey(processingNode)) continue;

            final Node copiedNode = new Node(processingNode.val);
            map.put(processingNode, copiedNode);

            for (int i = 0; i < processingNode.neighbors.size(); i++) {
                if (map.containsKey(processingNode.neighbors.get(i))) continue;
                queue.add(processingNode.neighbors.get(i));
            }
        }

        for (Map.Entry<Node, Node> entry : map.entrySet()) {
            final Node originalNode = entry.getKey();
            final Node copiedNode = entry.getValue();

            for (int i = 0; i < originalNode.neighbors.size(); i++) {
                copiedNode.neighbors.add(map.get(originalNode.neighbors.get(i)));
            }
        }

        return map.get(node);
    }
}
