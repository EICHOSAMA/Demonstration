package per.eicho.demo.leetcode.q301_400;

/**
 * <p>316. Remove Duplicate Letters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-height-trees/">
 *   316. Remove Duplicate Letters</a>
 */
public final class Q316 {
    public String removeDuplicateLetters(String s) {
        // 1. 1 <= s.length <= 10^4
        // 2. s consists of lowercase English letters.        
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
