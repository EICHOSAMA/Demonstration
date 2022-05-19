package per.eicho.demo.leetcode.q401_500;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>427. Construct Quad Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/construct-quad-tree/">
 *   427. Construct Quad Tree</a>
 */
@SuppressWarnings("unused")
public final class Q427 {

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    
        
        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
        
        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    private static class Info {
        private final int len;
        private final int i;
        private final int j;
        private final Node node;
        Info(int len, int i, int j, Node node) {
            this.i = i;
            this.j = j;
            this.len = len;
            this.node = node;
        }
    }

    public Node construct(int[][] grid) {
        // 1. n == grid.length == grid[i].length
        // 2. n == 2^x where 0 <= x <= 6        
        final int n = grid.length;
        final int[][] sums = new int[n][n];
        sums[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) sums[0][i] = sums[0][i - 1] + grid[0][i];
        for (int i = 1; i < n; i++) {
            sums[i][0] = grid[i][0];
            for (int j = 1; j < n; j++) sums[i][j] = sums[i][j - 1] + grid[i][j];
            for (int j = 0; j < n; j++) sums[i][j] += sums[i - 1][j];
        }

        final Queue<Info> processingQueue = new LinkedList<>();
        final Node root = new Node();
        processingQueue.add(new Info(n, 0, 0, root));

        while (!processingQueue.isEmpty()) {
            final Info info = processingQueue.poll();
            final Node node = info.node;            
            final int val = isLeaf(sums, info);
            if (val != -1) {
                node.isLeaf = true;
                node.val = val == 1 ? true : false;
            } else {
                node.isLeaf = false;                
                final int len = info.len / 2;
                processingQueue.add(new Info(len, info.i, info.j, node.topLeft = new Node()));
                processingQueue.add(new Info(len, info.i + len, info.j, node.bottomLeft = new Node()));
                processingQueue.add(new Info(len, info.i, info.j + len, node.topRight = new Node()));
                processingQueue.add(new Info(len, info.i + len, info.j + len, node.bottomRight = new Node()));
            }
        }

        return root;
    }

    private int isLeaf(int[][] sums, Info info) {
        final int lx = info.i;
        final int ly = info.j;
        final int rx = info.i + info.len - 1;
        final int ry = info.j + info.len - 1;

        final int sum = 
            sums[rx][ry] + getSum(sums, lx - 1, ly - 1) - 
            getSum(sums, rx, ly - 1) - getSum(sums, lx - 1, ry);

        return sum == 0 ? 0 : (sum == info.len * info.len ? 1 : -1);
    }

    private int getSum(int[][] sums, int i, int j) {
        if (i < 0 || j < 0) return 0;
        return sums[i][j];
    }
}
