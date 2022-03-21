package per.eicho.demo.leetcode.q201_300;

import java.util.Arrays;

/**
 * <p>204. Count Primes 的题解代码 </p>
 * 
 * <pre>
 *  质数: prime
 *  合数: composite 
 * </pre>
 * 
 * @see <a href="https://leetcode.com/problems/count-primes/">
 *   204. Count Primes</a>
 */
public final class Q204 {
    public int countPrimes(int n) {
        // 1. 0 <= n <= 5 * 10^6
        n--; // Count the number of prime numbers less than a non-negative number, n.
        final boolean[] isPrime = new boolean[n+1]; // [0-n]
        Arrays.fill(isPrime, true);

        int result = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i] == false) continue;
            result++;
            for (int j = i * 2; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
        return result;
    }
}
