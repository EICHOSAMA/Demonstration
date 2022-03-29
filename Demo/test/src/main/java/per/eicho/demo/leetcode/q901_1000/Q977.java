package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>977. Squares of a Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/squares-of-a-sorted-array/">977. Squares of a Sorted Array</a>
 */
public class Q977 {
    public int[] sortedSquares(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^4 <= nums[i] <= 10^4
        // 3. nums is sorted in non-decreasing order.        
        final int n = nums.length;
        final int[] result = new int[n];

        int idx = binarySearch(nums);

        int l = idx - 1;
        int r = idx;

        int p = 0;
        while (l >= 0 || r < n) {

            if (l < 0) { // run out of num less than zero.
                while (r < n) {
                    result[p++] = square(nums[r++]);
                }
                break;
            }

            if (r >= n) { // run out of num greater than zero.
                while (l >= 0) {
                    result[p++] = square(nums[l--]);
                }
                break;
            }

            if (nums[r] + nums[l] < 0) {
                result[p++] = square(nums[r++]); // use right element
            } else {
                result[p++] = square(nums[l--]); // use left element
            }
        }


        return result;
    }

    private int square(int num) {
        return num * num;
    } 

    private int binarySearch(int[] nums) {

        if (nums[0] > 0) return 0;
        if (nums[nums.length - 1] < 0) return nums.length;

        int l = 0, r = nums.length - 1;
        while (l < r) {
            final int mid = (l + r) / 2;
            final int numMid = nums[mid];

            if (numMid < 0) {
                l = mid + 1;
            } else {
                // numMid >= 0;
                r = mid;
            }
        }
        return l;
    }
}
