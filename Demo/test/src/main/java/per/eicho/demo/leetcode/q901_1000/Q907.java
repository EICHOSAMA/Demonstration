package per.eicho.demo.leetcode.q901_1000;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <p>907. Sum of Subarray Minimums 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-subarray-minimums/">
 *   907. Sum of Subarray Minimums</a>
 */
public final class Q907 {
    public int sumSubarrayMins(int[] arr) {
        // 1. 1 <= arr.length <= 3 * 10^4
        // 2. 1 <= arr[i] <= 3 * 10^4
        final int n = arr.length;
        final int[] lengths = new int[n];
        final LinkedList<Integer> monoStack = new LinkedList<>(); // storing index info.

        int p = 0;
        while (p < n) {
            if (monoStack.isEmpty()) {
                monoStack.addLast(p++);
                continue;
            }

            final int num = arr[p];
            while (!monoStack.isEmpty() && arr[monoStack.peekLast()] > num) {
                final int idx = monoStack.pollLast();
                lengths[idx] = p - idx;
            }
            monoStack.addLast(p++);
        }

        while (!monoStack.isEmpty()) {
            final int idx = monoStack.poll();
            lengths[idx] = p - idx;
        }

        long result = 0L;
        final long modulo = 1_000_000_007L;
        
        final int[] times = new int[n];
        Arrays.fill(times, 1);
        for (int i = 0; i < n; i++) {
            final long time = times[i];
            final int len = lengths[i];
            final long num = arr[i];

            result = (result + num * time * len) % modulo;
            if (i + len < n) {
                times[i + len] += time;
            }
        }

        return (int)(result % modulo);
    }

    public static void main(String[] args) {
        Q907 q907 = new Q907();
        System.out.println(q907.sumSubarrayMins(new int[]{11,81,94,43,3}));
    }
}
