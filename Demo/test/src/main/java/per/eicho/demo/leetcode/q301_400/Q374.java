package per.eicho.demo.leetcode.q301_400;

/**
 * <p>374. Guess Number Higher or Lower 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/guess-number-higher-or-lower/">374. Guess Number Higher or Lower</a>
 */
public abstract class Q374 {
    public int guessNumber(int n) {
        // 1 <= n <= 2^31 - 1
        // 1 <= pick <= n        
        int l = 1;
        int mid;
        while (l != n) {
            mid = getMiddle(l, n);
            int result = guess(mid);
            if (result == 0) return mid;
            if (result < 0) // 'My' number is lower, mid is higher, then search left 
                n = mid; 
            else // 'My' number is higher, mid is lower, then search right.
                l = mid + 1;
        }
        return l;
    }
    
    private int getMiddle(int l, int r) {
        return (int)(((long)l + (long)r) / 2L);
    }
    
    /**
     * The guess API is defined in the parent class GuessGame.
     * @param num, your guess
     * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0 int guess(int num);
     */
    abstract int guess(int num);
}
