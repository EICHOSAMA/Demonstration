package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1456. Maximum Number of Vowels in a Substring of Given Length 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/">
 *   1456. Maximum Number of Vowels in a Substring of Given Length</a>
 */
public final class Q1456 {

    private static final char[] vowelLetters = new char[]{'a', 'e', 'i', 'o', 'u'}; 

    public int maxVowels(String s, int k) {
        // 1. 1 <= s.length <= 10^5
        // 2. s consists of lowercase English letters.
        // 3. 1 <= k <= s.length
        int l = 0, r = 0; // [l, r)
        int sum = 0;
        int result = 0;
        final int n = s.length();
        while (r < n) {
            while (r < n && r - l < k) {
                sum += (isVowelLetter(s.charAt(r++)) ? 1 : 0); 
            }

            if (sum > result) result = sum;
            sum -= (isVowelLetter(s.charAt(l++)) ? 1 : 0);
        }

        return result;
    }

    private boolean isVowelLetter(char ch) {
        for (char c : vowelLetters) if (c == ch) return true;
        return false;
    }
}
