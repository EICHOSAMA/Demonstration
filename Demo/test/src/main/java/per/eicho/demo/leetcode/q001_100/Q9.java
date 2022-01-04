package per.eicho.demo.leetcode.q001_100;

/**
 * <p>9. Palindrome Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/palindrome-number/">9. Palindrome Number</a>
 */
public final class Q9 {
    private static byte[] digits = new byte[10];
    private static byte r;
    private static byte l;

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        r = 0;
        while (x != 0) {
            digits[r++] = (byte)(x % 10);
            x /= 10;
        }
        r--;
        l = 0;

        while (l < r) {
            if (digits[l++] != digits[r--]) return false;
        }

        return true;
    }
}
