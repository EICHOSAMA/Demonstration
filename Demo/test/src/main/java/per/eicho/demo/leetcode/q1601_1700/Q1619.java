package per.eicho.demo.leetcode.q1601_1700;

import java.util.Arrays;

/**
 * <p>1619. Mean of Array After Removing Some Elements 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/mean-of-array-after-removing-some-elements/">1619. Mean of Array After Removing Some Elements</a>
 */
public final class Q1619 {
    public double trimMean(int[] arr) {
        // 1. 20 <= arr.length <= 1000
        // 2. arr.length is a multiple of 20.
        // 3. 0 <= arr[i] <= 10^5
        Arrays.sort(arr);
        final int n = arr.length;
        final int fivePerOfN = n / 20;
        final int newN = n - fivePerOfN * 2;

        double sum = 0;
        for (int i = 0, j = fivePerOfN; i < newN; i++, j++) sum += arr[j];
        return sum / (double)newN;
    }
}
