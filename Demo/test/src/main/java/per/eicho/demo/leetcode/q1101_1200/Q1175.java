package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1175. Prime Arrangements 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/prime-arrangements/">1175. Prime Arrangements</a>
 */
public final class Q1175 {
    public int numPrimeArrangements(int n) {
        // 1. 1 <= n <= 100
        final int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
        final int MOD = 1000_000_007; // 10^9 + 7;

        int primeCount = 0;
        for (int i = 0; i < primes.length; i++) {
            if (primes[i] <= n) {
                primeCount++;
            } else {
                break;
            }
        }

        long result = 1;
        for (int i = 1; i <= primeCount; i++) result = (result * i) % MOD;
        for (int i = 1; i <= n - primeCount; i++) result = (result * i) % MOD;
        return (int)result;
    }
}
