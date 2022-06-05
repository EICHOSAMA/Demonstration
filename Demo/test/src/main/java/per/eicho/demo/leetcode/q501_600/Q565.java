package per.eicho.demo.leetcode.q501_600;

/**
 * <p>565. Array Nesting 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/array-nesting/">
 *   565. Array Nesting</a>
 */
public final class Q565 {
    public int arrayNesting(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 0 <= nums[i] < nums.length
        // 3. All the values of nums are unique.
        final int n = nums.length;
        final boolean[] mark = new boolean[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (mark[i] != true) result = Math.max(result, search(nums, mark, i));
        }
        return result;
    }

    private int search(int[] nums, boolean[] mark, int p) {
        if (mark[p] == true) return 0;
        mark[p] = true;
        return search(nums, mark, nums[p]) + 1;
    }

    public static void main(String[] args) {
        Q565 q565 = new Q565();
        System.out.println(q565.arrayNesting(new int[]{5,4,0,3,1,6,2}));
    }
}
