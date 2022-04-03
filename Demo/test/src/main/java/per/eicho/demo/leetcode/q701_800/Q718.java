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
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            final int windowLen = Math.min(m, n - i);
            result = Math.max(result, maxLength(nums1, nums2, i, 0, windowLen));
        }

        for (int i = 0; i < m; i++) {
            final int windowLen = Math.min(n, m - i);
            result = Math.max(result, maxLength(nums1, nums2, 0, i, windowLen));
        }
        return result;
    }

    public int maxLength(int[] nums1, int[] nums2, int offset1, int offset2, int windowLen) {
        int result = 0, len = 0;
        for (int i = 0; i < windowLen; i++) {
            if (nums1[offset1 + i] == nums2[offset2 + i]) {
                len++;
            } else {
                result = Math.max(result, len);
                len = 0;
            }
        }
        return Math.max(result, len);
    }

    public static void main(String[] args) {
        Q718 q718 = new Q718();
        // System.out.println(q718.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
        System.out.println(q718.findLength(new int[]{0,0,0,0,1}, new int[]{1,0,0,0,0}));
    }
}
