package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1361. Validate Binary Tree Nodes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/matrix-block-sum/">
 *   1361. Validate Binary Tree Nodes</a>
 */
public final class Q1361 {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // 1. n == leftChild.length == rightChild.length
        // 2. 1 <= n <= 10^4
        // 3. -1 <= leftChild[i], rightChild[i] <= n - 1
        final int[] in = new int[n];
        int count0 = n;

        for (int i = 0; i < n; i++) {
            final int lC = leftChild[i];
            final int rC = rightChild[i];

            if (lC != -1) {
                if (++in[lC] > 1) {
                    return false;
                } else {
                    count0--;
                }
            }

            if (rC != -1) {
                if (++in[rC] > 1) {
                    return false;
                } else {
                    count0--;
                }
            }
        }

        if (count0 != 1) return false;
        

        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                final int count = count(i, leftChild, rightChild);
                return count == n;
            }
        }
        return false;
    }

    private int count(int node, int[] leftChild, int[] rightChild) {
        if (node == -1) return 0;
        return 1
            + count(leftChild[node], leftChild, rightChild)
            + count(rightChild[node], leftChild, rightChild);
    }
}
