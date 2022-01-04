package per.eicho.demo.leetcode.q001_100;

/**
 * <p>5. Longest Palindromic Substring 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-palindromic-substring/">5. Longest Palindromic Substring</a>
 */
public final class Q5 {
    public String longestPalindrome(String s) {
        final int len = s.length();
        if (len == 0) return s;

        int maxLength = 1;
        int maxIndex = 0; // s[0] is the longest palindrome substring at first with a maxLength 1.

        int cursor = 0; // processing index.
        while (cursor < len) {
            // search [cursor + maxLength, len -1.
            final int l = cursor + maxLength; // inclusive
            int r = len - 1; // inclusive.
            while (r >= l) {
                /**
                 * Judge a string's substring at range [l, r] is palindrome or not.
                 * @param s target string
                 * @param l index of left, inclusive.
                 * @param r index of right, inclusive.
                 * @return the specific range of the string is palindrome of not.
                 */
                boolean isPalindrome = true;
                final int mid = (l + r - 1) / 2;
                int cl = cursor, cr = r;
                while (cl <= mid) {
                    if (s.charAt(cl++) != s.charAt(cr--)) {
                        isPalindrome = false;
                        break;
                    }
                }

                if (isPalindrome) {
                    // update max length and the longest substring's start Index.
                    maxIndex = cursor;
                    maxLength = r - cursor + 1;
                    break; // stop searching.
                }
                r--;
            }

            cursor++; // move cursor.
        }

        return  s.substring(maxIndex, maxIndex + maxLength);
    }
}
