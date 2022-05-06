package per.eicho.demo.leetcode.q601_700;

/**
 * <p>665. Non-decreasing Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/non-decreasing-array/">
 *   665. Non-decreasing Array</a>
 */
public final class Q665 {
    public boolean checkPossibility(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 10^4
        // 3. -10^5 <= nums[i] <= 10^5        
        final int n = nums.length;
        if (n <= 2) return true;
        
        int i = 0;
        while (i < n - 1 && nums[i] <= nums[i + 1]) i++;
        if (i == n - 1) return true;

        final int a = nums[i];
        final int b = nums[i + 1];
        final int p = i;
        nums[i] = b;

        i = 0;
        while (i < n - 1 && nums[i] <= nums[i + 1]) i++;
        if (i == n - 1) return true;

        nums[p] = a;
        nums[p + 1] = a;
        
        i = 0;
        while (i < n - 1 && nums[i] <= nums[i + 1]) i++;
        return i == n - 1;
    }

    public static void main(String[] args) {
        Q665 q665 = new Q665();
        System.out.println(q665.checkPossibility(new int[]{5,7,1,8}));
    }
}
