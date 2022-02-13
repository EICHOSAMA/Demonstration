package per.eicho.demo.leetcode.q001_100;

/**
 * <p>50. Pow(x, n) 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/powx-n/">50. Pow(x, n)</a>
 */
public final class Q50 {
    public double myPow(double x, int n) {
        // 1. -100.0 < x < 100.0
        // 2. -23^1 <= n <= 2^31-1
        // 3. -10^4 <= x^n <= 10^4        
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;

        final int mid = n / 2;

        if (mid * 2 == n) {
            // n is even.
            double powXMID = myPow(x, mid);
            return powXMID * powXMID;
        } else {
            // n is odd.
            double powXMID = myPow(x, mid);
            return powXMID * powXMID * myPow(x, n - mid * 2);
        }    
    }
}
