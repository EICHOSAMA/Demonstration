package per.eicho.demo.leetcode.q701_800;

/**
 * <p>746. Min Cost Climbing Stairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/min-cost-climbing-stairs/">746. Min Cost Climbing Stairs</a>
 */
public final class Q746 {
    public int minCostClimbingStairs(int[] cost) {
        final int[] f = new int[cost.length + 2];
        f[0] = 0;
        f[1] = 0;
        for (int i = 2; i < f.length; i++) {
            f[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < cost.length; i++) {
            final int newCost = f[i] + cost[i];
            f[i + 1] = Math.min(f[i + 1], newCost);
            f[i + 2] = Math.min(f[i + 2], newCost);
        }
        return f[cost.length];
    }

    public static void main(String[] args) {
        Q746 q746 = new Q746();

        int result = q746.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1});
        System.out.println(result);
    }
}
