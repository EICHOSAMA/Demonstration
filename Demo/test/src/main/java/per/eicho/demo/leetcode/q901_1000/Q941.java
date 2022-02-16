package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>941. Valid Mountain Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-mountain-array/">941. Valid Mountain Array</a>
 */
public final class Q941 {
    public boolean validMountainArray(int[] arr) {
        // 1. 1 <= arr.length <= 10^4
        // 2. 0 <= arr[i] <= 10^4        
        final int n = arr.length;
        if (n <= 2) return false;

        int l = 0, r = n - 1;
        while (l + 1 < n && arr[l] < arr[l + 1]) l++;
        while (r - 1 >= 0 && arr[r - 1] > arr[r]) r--;
        return l == r && 0 < l && l < n - 1;
    }
}
