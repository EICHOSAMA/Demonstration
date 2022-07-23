package per.eicho.demo.leetcode.q701_800;

import java.util.PriorityQueue;

/**
 * <p>786. K-th Smallest Prime Fraction 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/k-th-smallest-prime-fraction/">
 *   786. K-th Smallest Prime Fraction</a>
 */
public final class Q786 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 1. 2 <= arr.length <= 1000
        // 2. 1 <= arr[i] <= 3 * 10^4
        // 3. arr[0] == 1
        // 4. arr[i] is a prime number for i > 0.
        // 5. All the numbers of arr are unique and sorted in strictly increasing order.
        // 6. 1 <= k <= arr.length * (arr.length - 1) / 2
        final int n = arr.length;
        final PriorityQueue<int[]> minHeap = new PriorityQueue<>((f1, f2) -> {
            final int numerator1 = arr[f1[0]];
            final int denominator1 = arr[f1[1]];
            final int numerator2 = arr[f2[0]];
            final int denominator2 = arr[f2[1]];
            return numerator1 * denominator2 - numerator2 * denominator1;
        });

        for (int i = 0, bound = n - 1; i < bound; i++) minHeap.offer(new int[]{i, bound});

        int[] idxInfo;
        while (k > 1) {
            idxInfo = minHeap.poll();
            k--;
            idxInfo[1]--;
            if (idxInfo[0] < idxInfo[1]) minHeap.offer(idxInfo);
        }

        idxInfo = minHeap.peek();
        return new int[]{arr[idxInfo[0]], arr[idxInfo[1]]};
    }
}
