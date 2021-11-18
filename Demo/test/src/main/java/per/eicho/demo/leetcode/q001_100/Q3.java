package per.eicho.demo.leetcode.q1_100;


/**
 * <p>3. Longest Substring Without Repeating Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">3. Longest Substring Without Repeating Characters</a>
 */
final public class Q3 {
    public int lengthOfLongestSubstring(String s) {
        // index represent the codepoint(0-65535) of a UNICODE character which is supported by Java.
        // value represent the appear index+1 of the character. +1 caz initialize value is 0.
        int[] appearance = new int[256];

        int nowLength = 0;
        int maxLength = 0;

        final int size = s.length();
        for (int i = 0; i < size; ) {
            // 1. gather inforamtion
            //    ch: current character
            //    indexP1: index of previous 'ch'
            final char ch = s.charAt(i);
            final int indexP1 = appearance[ch];

            // 2. main process
            if (0 == indexP1) {
                // CASE1: current character not existing.
                // 0 means char not existing.
                // update nowLength;
                nowLength++;
            } else if (indexP1 > i - nowLength) {
                // CASE2: current string contains the previous character, 
                // which is judge by the information 'nowLength', 
                // in this case we should cut those chars before indexP1 (inclusive).
                nowLength = i - indexP1 + 1;
            } else {
                // CASE3: previous character existing but out of the range of current string
                // inb this case just upadte nowLength.
                nowLength++;
            }

            // 3. maintenance information
            // record max length when necessary.
            if (nowLength > maxLength)
                maxLength = nowLength;

            // record current.
            appearance[ch] = ++i; 
        }

        return maxLength;
    }
}
