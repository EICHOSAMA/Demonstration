package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2108. Find First Palindromic String in the Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-first-palindromic-string-in-the-array/">
 *   2108. Find First Palindromic String in the Array</a>
 */
public final class Q2108 {
    public String firstPalindrome(String[] words) {
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (isPalindrome(word)) return word;
        }
        return "";
    }

    private boolean isPalindrome(String word) {
        int l = 0;
        int r = word.length() -1;

        while (l < r) {
            if (word.charAt(l++) != word.charAt(r--)) return false;
        }
        return true;
    }
}
