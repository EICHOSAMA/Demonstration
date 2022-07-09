package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1081. Smallest Subsequence of Distinct Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/">
 *   1081. Smallest Subsequence of Distinct Characters</a>
 */
public final class Q1081 {
    public String smallestSubsequence(String s) {
        // 1. 1 <= s.length <= 1000
        // 2. s consists of lowercase English letters.        
        // This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/
        final int[] count = new int[26];
        int charCount = 0;
        for (int i = 0; i < s.length(); i++) {
            final int ch = s.charAt(i) - 'a';
            count[ch]++;
            if (count[ch] == 1) charCount++;
        }

        final int[] stack = new int[charCount];
        int stackSize = 0;
        final boolean[] marks = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            final int ch = s.charAt(i) - 'a';

            // if not marked as using.
            if (!marks[ch]) {
                while (stackSize > 0 && stack[stackSize - 1] > ch) {
                    if (count[stack[stackSize - 1]] > 0) {
                        marks[stack[stackSize - 1]] = false;
                        stackSize--;
                    } else {
                        break;
                    }
                }

                stack[stackSize++] = ch;
                marks[ch] = true;
            }

            // maintain count info.
            count[ch]--;
        }
        
        final char[] chars = new char[charCount];
        for (int i = 0; i < charCount; i++) {
            chars[i] = (char)(stack[i] + 'a');
        }

        return String.valueOf(chars);
    }
}
