package per.eicho.demo.leetcode.q401_500;

/**
 * <p>440. K-th Smallest in Lexicographical Order 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/">
 *   440. K-th Smallest in Lexicographical Order</a>
 */
public final class Q440 {
    public int findKthNumber(int n, int k) {
        // 1. 1 <= k <= n <= 10^9
        int current = 1;
        k--;
        while (k > 0) {
            final int count = countNums(current, n);

            if (count <= k) {
                k -= count; // skip ${count} steps.
                current++; // last digit++ ;
            } else {
                current = current * 10; // ensure current digit
                k--;
            }
        }
        return current;
    }

    private int countNums(int root, long n) {
        int count = 0;
        long l = root, r = l;
        while (l <= n) {
            count += Math.min(r, n) - l + 1;
            l *= 10;
            r = r * 10 + 9;
        }
        return count;
    }
}
