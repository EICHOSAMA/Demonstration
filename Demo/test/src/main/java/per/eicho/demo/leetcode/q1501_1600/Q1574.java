package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1574. Shortest Subarray to be Removed to Make Array Sorted 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/">
 *   1574. Shortest Subarray to be Removed to Make Array Sorted</a>
 */
public final class Q1574 {
    public int findLengthOfShortestSubarray(int[] arr) {
        // 1. 1 <= arr.length <= 10^5
        // 2. 0 <= arr[i] <= 10^9
        final int n = arr.length;
        int r = n - 1;
        while (r - 1 >= 0 && arr[r - 1] <= arr[r]) r--;
        int result = r;
        if (result == 0) return 0;
        int l = 0;
        do {
            while (r < n && arr[r] < arr[l]) r++;

            result = Math.min(result, r - l - 1);
            
            if (l + 1 < n && arr[l] <= arr[l + 1]) {
                l++;
            } else {
                break;
            }
        } while (l < n);
        return result;
    }
}
