package per.eicho.demo.leetcode.q701_800;

/**
 * <p>718. Maximum Length of Repeated Subarray 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-length-of-repeated-subarray/">718. Maximum Length of Repeated Subarray</a>
 */
public final class Q718 {
    public int findLength(int[] nums1, int[] nums2) {
        // 1. 1 <= nums1.length, nums2.length <= 1000
        // 2. 0 <= nums1[i], nums2[i] <= 100
        final int n = nums1.length;
        final int m = nums2.length;
        final int[][] f = new int[n + 1][m + 1];

        int result = 0;
        for (int i = 0; i < m; i++) {
            if (nums2[i] == nums1[0]) {
                f[0][i] = 1;
                result = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums2[0] == nums1[i]) {
                f[i][0] = 1;
                result = 1;
            }
        }
        
        for (int i = 1; i < n; i++) {
            final int num1 = nums1[i];
            for (int j = 1; j < m; j++) {
                final int num2 = nums2[j];
                if (num1 == num2) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    result = Math.max(result, f[i][j]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q718 q718 = new Q718();
        // System.out.println(q718.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
        System.out.println(q718.findLength(new int[]{0,0,0,0,1}, new int[]{1,0,0,0,0}));
    }
}
