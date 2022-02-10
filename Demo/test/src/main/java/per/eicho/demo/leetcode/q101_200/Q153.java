package per.eicho.demo.leetcode.q101_200;

/**
 * <p>153. Find Minimum in Rotated Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">153. Find Minimum in Rotated Sorted Array</a>
 */
public final class Q153 {
    public int findMin(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 5000
        // 3. -5000 <= nums[i] <= 5000
        // 4. All the integers of nums are unique.
        // 5. nums is sorted and rotated between 1 and n times.        
        final int n = nums.length;

        int l = 0, r = n - 1;
        while (nums[l] > nums[r]) {
            final int mid = (l + r) / 2;
            final int numMid = nums[mid];

            if (nums[l] <= numMid) {
                l = mid + 1; // move left pointer
            } else {
                r = mid;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        Q153 q153 = new Q153();
        System.out.println(q153.findMin(new int[]{1}));
    }
}
