package per.eicho.demo.leetcode.q301_400;

import java.util.Arrays;

/**
 * <p>313. Super Ugly Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/super-ugly-number/">
 *   313. Super Ugly Number</a>
 */
public final class Q313 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        // 1. 1 <= n <= 10^6
        // 2. 1 <= primes.length <= 100
        // 3. 2 <= primes[i] <= 1000
        // 4. primes[i] is guaranteed to be a prime number.
        // 5. All the values of primes are unique and sorted in ascending order.        
        final int[] f = new int[n + 1];
        final int m = primes.length;
        
        int[] pointers = new int[m];
        int[] candidates = new int[m];
        Arrays.fill(candidates, 1);
        int nextMinNum = 1;

        for (int i = 1; i <= n; i++) {
            final int minNum = nextMinNum;
            f[i] = minNum;

            nextMinNum = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (candidates[j] == minNum) {
                    pointers[j]++;
                    candidates[j] = f[pointers[j]] * primes[j];
                }
                nextMinNum = Math.min(nextMinNum, candidates[j]);
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        Q313 q313 = new Q313();
        System.out.println(q313.nthSuperUglyNumber(20, new int[]{2,7,13,19}));
    }
}
