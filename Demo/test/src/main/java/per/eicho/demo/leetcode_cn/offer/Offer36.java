package per.eicho.demo.leetcode_cn.offer;

/**
 * <p>剑指 Offer 36. 二叉搜索树与双向链表 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/">
 *   剑指 Offer 36. 二叉搜索树与双向链表</a>
 */
@SuppressWarnings("unused")
public final class Offer36 {

    private static final class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        return _treeToDoublyList(root).left;
    }

    private Node _treeToDoublyList(Node root) {
        if (root == null) return null;
        // 数据规模不详
        Node left = _treeToDoublyList(root.left);
        Node right = _treeToDoublyList(root.right);
        if (left == null && right == null) {
            root.left = root;
            root.right = root;
            return new Node(-1, root.left, root.right);
        } else if (left == null) {
            root.left = right.right;
            right.right.right = root;

            root.right = right.left;
            right.left.left = root;
            return new Node(-1, root, right.right);
        } else if (right == null) {
            root.left = left.right;
            left.right.right = root;

            root.right = left.left;
            left.left.left = root;
            return new Node(-1, left.left, root);
        } else {
            root.left = left.right;
            root.right = right.left;

            root.left.right = root;
            root.right.left = root;

            left.left.left = right.right;
            right.right.right = left.left;
            return new Node(-1, left.left, right.right);
        }
    }
}
