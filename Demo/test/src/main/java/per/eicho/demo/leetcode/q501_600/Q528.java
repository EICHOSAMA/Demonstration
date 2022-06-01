package per.eicho.demo.leetcode.q501_600;

import java.util.Random;

/**
 * <p>528. Random Pick with Weight 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/random-pick-with-weight/">
 *   528. Random Pick with Weight</a>
 */
public final class Q528 {
    private static final class Solution {

        final int[] sum;
        final int n;
        final Random random;
        final int total;

        public Solution(int[] w) {
            // 1. 1 <= w.length <= 10^4
            // 2. 1 <= w[i] <= 10^5
            n = w.length;

            sum = new int[n];
            sum[0] = w[0];
            for (int i = 1; i < n; i++) sum[i] = sum[i - 1] + w[i];
            random = new Random(System.currentTimeMillis());
            total = sum[n - 1];
        }
        
        /** 
         * randomly picks an index in the range [0, w.length - 1] and returns it. 
         * The probability of picking an index i is w[i] / sum(w).
         */
        public int pickIndex() {
            // 3. pickIndex will be called at most 10^4 times.
            if (n == 1) return 0;
            final int num = random.nextInt(total) + 1; // num ∈ [1, total]
            
            if (num <= sum[0]) return 0;
            return binarySearch(num, 0, n - 1);
        }

        private int binarySearch(final int target, int l, int r) {
            // [l, r]
            if (l + 1 == r) return r;
            final int mid = (l + r + 1) / 2;
            
            if (target > sum[mid]) return binarySearch(target, mid, r);
            // target <= sum[mid];
            return binarySearch(target, l, mid);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1,3,1});
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        
    }
}
