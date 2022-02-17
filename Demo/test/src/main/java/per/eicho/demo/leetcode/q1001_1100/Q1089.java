package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1089. Duplicate Zeros 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/duplicate-zeros/">
 *   1089. Duplicate Zeros</a>
 */
public final class Q1089 {
    public void duplicateZeros(int[] arr) {
        // 1. 1 <= arr.length <= 10^4
        // 2. 0 <= arr[i] <= 9
        final int[] copy = arr.clone();
        int p1 = 0, p2 = 0;
        while (p1 < arr.length) {
            if (copy[p2] != 0) {
                arr[p1++] = copy[p2++];
                continue;
            } else {
                p2++;
                arr[p1++] = 0;
                if (p1 < arr.length) arr[p1++] = 0;
            }
        }
    }
}
