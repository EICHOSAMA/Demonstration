package per.eicho.demo.leetcode.q101_200;

/**
 * <p>125. Valid Palindrome 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-palindrome/">125. Valid Palindrome</a>
 */
public final class Q125 {
    public boolean isPalindrome(String s) {
        // 1. 1 <= s.length <= 2 * 10^5
        // 2. s consists only of printable ASCII characters.        
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (Character.isAlphabetic(s.charAt(l)) == false &&
                   Character.isDigit(s.charAt(l)) == false &&
                   l < r) {
                l++;
            }

            while (Character.isAlphabetic(s.charAt(r)) == false &&
                   Character.isDigit(s.charAt(r)) == false &&
                   r > l) {
                r--;
            }

            if (l >= r) break;

            if (Character.toUpperCase(s.charAt(l++)) != Character.toUpperCase(s.charAt(r--)))
                return false;
        }
        return true;
    }
}
