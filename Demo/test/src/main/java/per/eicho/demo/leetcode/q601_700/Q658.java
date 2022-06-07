package per.eicho.demo.leetcode.q601_700;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>658. Find K Closest Elements 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-k-closest-elements/">
 *   658. Find K Closest Elements</a>
 */
public final class Q658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 1. 1 <= k <= arr.length
        // 2. 1 <= arr.length <= 10^4
        // 3. arr is sorted in ascending order.
        // 4. -10^4 <= arr[i], x <= 10^4
        final int n = arr.length;
        final LinkedList<Integer> result = new LinkedList<>();
        int l, r;
        if (x <= arr[0]) {
            l = r = 0;
        } else if (x >= arr[n - 1]) {
            l = r = n - 1;
        } else {
            for (l = 0; l < n; l++) if (arr[l] > x) break;
            if (arr[l] - x >= x - arr[l - 1]) l--;
            r = l;
        }
        result.add(arr[l]);

        while (r - l + 1 < k) {
            if (l == 0) {
                while (r - l + 1 < k) result.addLast(arr[++r]);
                break;
            } else if (r == n - 1) {
                while (r - l + 1 < k) result.addFirst(arr[--l]);
                break;
            }

            final int candidateL = arr[l - 1];
            final int candidateR = arr[r + 1];
            if (Math.abs(candidateL - x) <= Math.abs(candidateR - x)) {
                // add left;
                result.addFirst(arr[--l]);
            } else {
                // add right;
                result.addLast(arr[++r]);
            }
        }
        return result;
    }
}
