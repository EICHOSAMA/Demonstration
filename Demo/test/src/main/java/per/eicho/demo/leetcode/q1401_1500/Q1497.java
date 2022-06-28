package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1497. Check If Array Pairs Are Divisible by k 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/">
 *   1497. Check If Array Pairs Are Divisible by k</a>
 */
public final class Q1497 {
    public boolean canArrange(int[] arr, int k) {
        // 1. arr.length == n
        // 2. 1 <= n <= 10^5
        // 3. n is even.
        // 4. -10^9 <= arr[i] <= 10^9
        // 5. 1 <= k <= 10^5

        // [0, k)
        final int[] counts = new int[k];
        for (int num : arr) counts[((num % k) + k) % k]++;
        if (counts[0] % 2 == 1) return false;
        int l = 1, r = k - 1;
        while (l < r) {
            if (counts[l] != counts[r]) return false;
            l++; r--;
        }
        if (l == r) return counts[l] % 2 == 0;
        return true;
    }
}
