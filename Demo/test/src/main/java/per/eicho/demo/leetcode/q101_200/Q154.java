package per.eicho.demo.leetcode.q101_200;

/**
 * <p>154. Find Minimum in Rotated Sorted Array II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/">154. Find Minimum in Rotated Sorted Array II</a>
 */
public final class Q154 {
    public int findMin(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 5000
        // 3. -5000 <= nums[i] <= 5000
        // 4. nums is sorted and rotated between 1 and n times.    
        final int n = nums.length;

        int l = 0, r = n - 1;
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            final int numMid = nums[mid];

            if (numMid < nums[r]) {
                r = mid;
            } else if (numMid > nums[r]) {
                l = mid + 1;
            } else {
                // numMid == nums[r]
                r--;
            }
        }

        return nums[r];
    }

    public static void main(String[] args) {
        Q154 q154 = new Q154();
        System.out.println(q154.findMin(new int[]{2,2,2,2,1,2,2,2,2,2,2,2}));
        System.out.println(q154.findMin(new int[]{2,2,2,2,2,2,2,2,2,2,2,2}));
        System.out.println(q154.findMin(new int[]{1}));
        
    }
}
