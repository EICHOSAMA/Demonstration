package per.eicho.demo.leetcode.q701_800;

/**
 * <p>769. Max Chunks To Make Sorted 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/max-chunks-to-make-sorted/">
 *   769. Max Chunks To Make Sorted</a>
 */
public final class Q769 {
    public int maxChunksToSorted(int[] arr) {
        // 1. n == arr.length
        // 2. 1 <= n <= 10
        // 3. 0 <= arr[i] < n
        // 4. All the elements of arr are unique.
        final int n = arr.length;
        int l = 0, r = arr[0];
        int result = 1;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            if (num > r) {
                r = num;
            }

            if (i == r && r + 1 < n) {
                l = r + 1;
                r = arr[i + 1];
                result++;
            }
        }
        
        return result;
    }
}
