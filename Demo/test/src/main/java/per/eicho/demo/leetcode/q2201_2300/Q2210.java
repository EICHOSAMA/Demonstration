package per.eicho.demo.leetcode.q2201_2300;

/**
 * <p>2210. Count Hills and Valleys in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-hills-and-valleys-in-an-array/">2210. Count Hills and Valleys in an Array</a>
 */
public final class Q2210 {
    public int countHillValley(int[] nums) {
        // 1. 3 <= nums.length <= 100
        // 2. 1 <= nums[i] <= 100
        final int n = nums.length;
        int count = 0;
        for (int i = 1; i < n; ) {
            final int num = nums[i];
            int j = i;
            while (j < n && nums[j] == num) j++;
            // [i, j)
            
            if (j == n) break;
            if ((nums[i - 1] < num && num > nums[j]) ||
                (nums[i - 1] > num && num < nums[j])) {
                count++;
            }
            i = j;
        }
        
        return count;
    }

    public static void main(String[] args) {
        Q2210 q2210 = new Q2210();
        System.out.println(q2210.countHillValley(new int[]{2,4,1,1,6,5}));
    }
}
