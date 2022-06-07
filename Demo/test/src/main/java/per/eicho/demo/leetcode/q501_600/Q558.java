package per.eicho.demo.leetcode.q501_600;

/**
 * <p>558. Logical OR of Two Binary Grids Represented as Quad-Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/">
 *   558. Logical OR of Two Binary Grids Represented as Quad-Trees</a>
 */
@SuppressWarnings("unused")
public final class Q558 {

    private final static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    
        public Node() {}
    
        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    public Node intersect(Node quadTree1, Node quadTree2) {
        // 1. quadTree1 and quadTree2 are both valid Quad-Trees each representing a n * n grid.
        // 2. n == 2^x where 0 <= x <= 9.
        if (quadTree1.isLeaf && quadTree2.isLeaf) return new Node(quadTree1.val | quadTree2.val, true, null, null, null, null);
        if (!quadTree1.isLeaf && !quadTree2.isLeaf) {
            return mergeSubNode(new Node(false, false, 
                intersect(quadTree1.topLeft, quadTree2.topLeft), 
                intersect(quadTree1.topRight, quadTree2.topRight), 
                intersect(quadTree1.bottomLeft, quadTree2.bottomLeft), 
                intersect(quadTree1.bottomRight, quadTree2.bottomRight)));
        }

        final Node quadTree = quadTree1.isLeaf ? quadTree2 : quadTree1;
        final boolean val = quadTree1.isLeaf ? quadTree1.val : quadTree2.val;
        return mergeSubNode(new Node(false, false, 
                intersect(quadTree.topLeft, val), intersect(quadTree.topRight, val), 
                intersect(quadTree.bottomLeft, val), intersect(quadTree.bottomRight, val)));
    }

    private Node intersect(Node quadTree, boolean val) {
        if (val == true) return new Node(true, true, null, null, null, null);
        if (quadTree.isLeaf) return new Node(quadTree.val | val, true, null, null, null, null);
        return mergeSubNode(new Node(false, false, 
                intersect(quadTree.topLeft, val), intersect(quadTree.topRight, val), 
                intersect(quadTree.bottomLeft, val), intersect(quadTree.bottomRight, val)));
    }

    private Node mergeSubNode(Node node) {
        if (node.isLeaf == true) return node;
        if (node.topLeft.isLeaf && node.topRight.isLeaf &&
            node.bottomLeft.isLeaf && node.bottomRight.isLeaf) {
            final boolean val = node.topLeft.val;
            if (node.topRight.val == val && node.bottomLeft.val == val && node.bottomRight.val == val) {
                node.topLeft = node.topRight = node.bottomLeft = node.bottomRight = null;
                node.isLeaf = true;
                node.val = val;
            }
        }
        return node;
    }
}
