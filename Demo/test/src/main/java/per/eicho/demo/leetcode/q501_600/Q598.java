package per.eicho.demo.leetcode.q501_600;

/**
 * <p>598. Range Addition II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/range-addition-ii/">598. Range Addition II</a>
 */
final public class Q598 {
    public int maxCount(int m, int n, int[][] ops) {
        /*
          1 <= m, n <= 4 * 104
          0 <= ops.length <= 104
          ops[i].length == 2
          1 <= ai <= m
          1 <= bi <= n
        */
        int minA = m;
        int minB = n;
        int count = 0;

        for (int i = 0; i < ops.length; i++) {
            final int ai = ops[i][0];
            final int bi = ops[i][1];

            count++;
            if (ai < minA) {
                minA = ai;
            }
            if (bi < minB) {
                minB = bi;
            }
        }

        return count * minA * minB;
    }
}
