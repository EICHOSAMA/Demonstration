package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1539. Kth Missing Positive Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-missing-positive-number/">
 *   1539. Kth Missing Positive Number</a>
 */
public final class Q1539 {
    public int findKthPositive(int[] arr, int k) {
        // 1. 1 <= arr.length <= 1000
        // 2. 1 <= arr[i] <= 1000
        // 3. 1 <= k <= 1000
        // 4. arr[i] < arr[j] for 1 <= i < j <= arr.length        
        final int n = arr.length;

        int num = 1;
        int p = 0;
        while (k != 0) {
            if (p < n && arr[p] == num) {
                p++;
            } else {
                k--;
            }
            num++;
        }

        return num - 1;
    }
}
