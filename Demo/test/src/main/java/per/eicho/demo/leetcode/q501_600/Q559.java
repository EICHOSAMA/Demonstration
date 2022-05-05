package per.eicho.demo.leetcode.q501_600;

import java.util.List;

/**
 * <p>559. Maximum Depth of N-ary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-n-ary-tree/">559. Maximum Depth of N-ary Tree</a>
 */
public final class Q559 {

    @SuppressWarnings("unused")
    private static class Node {
        public int val;
        public List<Node> children;
    
        public Node() {}
    
        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        // 1. The total number of nodes is in the range [0, 10^4].
        // 2. The depth of the n-ary tree is less than or equal to 1000.        
        if (root == null) return 0;

        if (root.children == null || root.children.size() == 0) return 1;

        int maxDepthOfChildren = 0;
        for (Node child: root.children) {
            final int depth = maxDepth(child);
            maxDepthOfChildren = Math.max(maxDepthOfChildren, depth);
        }

        return maxDepthOfChildren + 1;        
    }
}
