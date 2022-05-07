package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1534. Count Good Triplets 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-good-triplets/">
 *   1534. Count Good Triplets</a>
 */
public final class Q1534 {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        // 1. 3 <= arr.length <= 100
        // 2. 0 <= arr[i] <= 1000
        // 3. 0 <= a, b, c <= 1000
        final int n = arr.length;
        int num = 0;            
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(arr[i] - arr[j]) > a) continue; 

                for (int k = j + 1; k < n; k++) {
                    if (Math.abs(arr[j] - arr[k]) > b) continue;                     
                    if (Math.abs(arr[i] - arr[k]) > c) continue;                     
                    num++;
                }
            }
        }

        return num;
    }
}
