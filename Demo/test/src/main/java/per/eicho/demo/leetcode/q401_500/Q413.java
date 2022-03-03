package per.eicho.demo.leetcode.q401_500;

/**
 * <p>413. Arithmetic Slices 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/arithmetic-slices/">
 *   413. Arithmetic Slices</a>
 */
public final class Q413 {
    public int numberOfArithmeticSlices(int[] nums) {
        // 1. 1 <= nums.length <= 5000
        // 2. -1000 <= nums[i] <= 1000        
        final int n = nums.length;
        int i = 0;
        int result = 0;
        while (i < n - 2) {
            final int diff = nums[i] - nums[i + 1];
            if (nums[i + 1] - nums[i + 2] != diff) {
                i++;
                continue;
            }

            int r = i + 2;
            while (r + 1 < n && nums[r] - nums[r + 1] == diff) r++;

            result += numberOfArithmeticSlices(r - i + 1);
            i = r;
        }

        return result;
    }

    private int numberOfArithmeticSlices(int len) {
        int count = 0;
        int step = 1;
        while (len >= 3) {
            count += step++;
            len--;
        }
        return count;
    }

    public static void main(String[] args) {
        Q413 q413 = new Q413();
        System.out.println(q413.numberOfArithmeticSlices(new int[]{1,2,3,4}));
        System.out.println(q413.numberOfArithmeticSlices(new int[]{3,-1,-5,-9}));
    }
}
