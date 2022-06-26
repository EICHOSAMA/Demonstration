package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1031. Maximum Sum of Two Non-Overlapping Subarrays 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/">
 *   1031. Maximum Sum of Two Non-Overlapping Subarrays</a>
 */
public final class Q1031 {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // 1. 1 <= firstLen, secondLen <= 1000
        // 2. 2 <= firstLen + secondLen <= 1000
        // 3. firstLen + secondLen <= nums.length <= 1000
        // 4. 0 <= nums[i] <= 1000        
        final int n = nums.length;
        final int[] sums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) sums[i] = sum = sum + nums[i];
        final int[][] f = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            f[i][0] = f[i - 1][0];
            f[i][1] = f[i - 1][1];
            f[i][2] = f[i - 1][2];

            if (i - firstLen >= 0) {
                final int sumRange = sum(sums, i - firstLen, i - 1);
                f[i][0] = Math.max(f[i][0], sumRange);
                f[i][2] = Math.max(f[i][2], sumRange + f[i - firstLen][1]);
            }

            if (i - secondLen >= 0) {
                final int sumRange = sum(sums, i - secondLen, i - 1);
                f[i][1] = Math.max(f[i][1], sumRange);
                f[i][2] = Math.max(f[i][2], sumRange + f[i - secondLen][0]);    
            }
        } 

        return f[n][2];
    }

    private int sum(int[] sums, int l, int r) {
        int sum = sums[r];
        return sum - (l == 0 ? 0 : sums[l - 1]);
    }

    public static void main(String[] args) {
        Q1031 q1031 = new Q1031();
        System.out.println(q1031.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2));
        System.out.println(q1031.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 5, 4));
        System.out.println(q1031.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 4, 5));
    }
}
