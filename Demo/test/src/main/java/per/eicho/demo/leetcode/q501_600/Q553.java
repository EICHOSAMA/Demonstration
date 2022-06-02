package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;

/**
 * <p>553. Optimal Division 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/optimal-division/">
 *   553. Optimal Division</a>
 */
public final class Q553 {

    double[][] info;
    int[] f;

    public String optimalDivision(int[] nums) {
        // 1. 1 <= nums.length <= 10
        // 2. 2 <= nums[i] <= 1000
        // 3. There is only one optimal division for the given iput.
        final int n = nums.length;
        if (n == 1) return "" + nums[0];
        if (n == 2) return nums[0] + "/" + nums[1];
        info = new double[n][];
        f = new int[n];
        Arrays.fill(f, -1);

        info[0] = new double[]{nums[0], 1};
        info[1] = new double[]{nums[0], nums[1]};
        
        search(nums, n - 1);
        return buildResult(f, nums, n - 1);
    }

    private String buildResult(int[] f, int[] nums, int p) {
        if (p == 0) return "" + nums[0];
        if (p == 1) return nums[0] + "/" + nums[1];

        final StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = f[p] + 1; i < p; i++) {
            sb.append(nums[i]).append('/');
        }
        sb.append(nums[p]);
        sb.append(')');

        return buildResult(f, nums, f[p]) + "/" + sb.toString();
    }

    private double[] search(int[] nums, int p) {
        if (info[p] != null) return info[p];

        double[] l = search(nums, p - 2);
        final double[] r = new double[]{nums[p], nums[p - 1]}; // * nums[p] / nums[p - 1]

        int maxI = p - 2;
        double[] max = new double[]{l[0] * r[0], l[1] * r[1]};
        for (int i = p - 3; i >= 0; i--) {
            l = search(nums, i);
            
            r[0] *= nums[i + 2]; 
            r[1] = nums[i + 1];
        
            if (max[0] * l[1] * r[1] < max[1] * l[0] * r[0]) {
                max[0] = l[0] * r[0];
                max[1] = l[1] * r[1];
                maxI = i;
            }
        }

        f[p] = maxI;
        return max;
    }

    public static void main(String[] args) {
        Q553 q553 = new Q553();
        System.out.println(q553.optimalDivision(new int[]{2,1000,100,10,2,1000,1000,2,3,5}));
    }
}
