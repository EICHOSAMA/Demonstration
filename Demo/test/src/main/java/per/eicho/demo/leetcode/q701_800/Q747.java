package per.eicho.demo.leetcode.q701_800;

/**
 * <p>747. Largest Number At Least Twice of Others 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-number-at-least-twice-of-others/">747. Largest Number At Least Twice of Others</a>
 */
public final class Q747 {
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) return 0;

        int m1st = nums[0];
        int i1st = 0;

        int m2nd = -1;

        for (int i = 1; i < nums.length; i++) {
            final int num = nums[i];

            if (num > m1st) {
                m2nd = m1st;

                m1st = num;
                i1st = i;
            } else if (num > m2nd) {
                m2nd = num;
            }
        }

        return m1st >= 2 * m2nd ? i1st : -1 ;
    }
}
