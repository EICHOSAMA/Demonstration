package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1493. Longest Subarray of 1's After Deleting One Element 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/">
 *   1493. Longest Subarray of 1's After Deleting One Element</a>
 */
public final class Q1493 {
    public int longestSubarray(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. nums[i] is either 0 or 1.
        final int n = nums.length;
        if (n < 2) return 0;
        int l = 0, r = 1; // [l, r)
        int count = nums[0] == 0 ? 0 : 1;
        int result = 0;
        while (l < n) {
            while (r < n) {
                final int num = nums[r];
                if (num == 1) {
                    r++;
                    continue;
                }

                if (count > 0) {
                    count--;
                    r++;
                    continue;
                }

                break;
            }

            result = Math.max(result, r - l - 1);
            if (nums[l++] == 0) count++;
        }

        return result;
    }

    public static void main(String[] args) {
        Q1493 q1493 = new Q1493();
        // System.out.println(q1493.longestSubarray(new int[]{1,1,0,1}));
        System.out.println(q1493.longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
    }
}
