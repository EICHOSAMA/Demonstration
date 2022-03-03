package per.eicho.demo.leetcode.q1201_1300;

import java.util.Arrays;

/**
 * <p>1207. Unique Number of Occurrences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-number-of-occurrences/">
 *   1207. Unique Number of Occurrences</a>
 */
public final class Q1207 {
    public boolean uniqueOccurrences(int[] arr) {
        // 1. 1 <= arr.length <= 1000
        // 2. -1000 <= arr[i] <= 1000
        final int[] counts = new int[2001];
        final int OFFSET = 1000;

        for (int num : arr) {
            counts[num + OFFSET]++;
        }

        Arrays.sort(counts);
        int p = counts.length - 1;
        while (counts[p] != 0) {
            if (counts[p] == counts[--p]) return false; 
        }
        return true;
    }
}
