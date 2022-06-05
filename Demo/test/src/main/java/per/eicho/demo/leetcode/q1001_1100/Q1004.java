package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1004. Max Consecutive Ones III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/max-consecutive-ones-iii/">
 *   1004. Max Consecutive Ones III</a>
 */
public final class Q1004 {
    public int longestOnes(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^5
        // 2. nums[i] is either 0 or 1.
        // 3. 0 <= k <= nums.length
        final int n = nums.length;
        int remain = k;
        int l = 0, r = -1;
        while (r + 1 < n && (remain > 0 || nums[r + 1] == 1)) {
            r++;
            if (nums[r] == 0) remain--;
        }

        int result = r - l + 1;
        while (r + 1 < n) {
            while (r + 1 < n && (remain >= 0 || nums[r + 1] == 1)) {
                r++;
                if (nums[r] == 0) remain--;
            }

            while (remain < 0) {
                if (nums[l++] == 0) remain++;
            }

            // remain = 0;
            result = Math.max(result, r - l + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Q1004 q1004 = new Q1004();
        System.out.println(q1004.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 0));
        System.out.println(q1004.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
    }
}
