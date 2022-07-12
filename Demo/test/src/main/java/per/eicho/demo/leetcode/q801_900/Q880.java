package per.eicho.demo.leetcode.q801_900;

import java.util.Stack;

/**
 * <p>880. Decoded String at Index 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/decoded-string-at-index/">
 *   880. Decoded String at Index</a>
 */
public final class Q880 {
    public String decodeAtIndex(String s, int k) {
        // 1. 2 <= s.length <= 100
        // 2. s consists of lowercase English letters and digits 2 through 9.
        // 3. s starts with a letter.
        // 4. 1 <= k <= 10^9
        // 5. It is guaranteed that k is less than or equal to the length of the decoded string.
        // 6. The decoded string is guaranteed to have less than 263 letters.        
        return decodeAtIndex(s, (long)k);
    }

    public String decodeAtIndex(String s, long k) {
        final Stack<StringBuilder> words = new Stack<>();
        final Stack<Long> times = new Stack<>();
        boolean newWord = true;
        long bound = 0;
        for (int i = 0, n = s.length(); i < n; i++) {
            final char ch = s.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                if (newWord) {
                    words.add(new StringBuilder());
                    times.add(1L);
                    newWord = false;
                }

                words.peek().append(ch);
                bound++;
            } else {
                newWord = true;
                final long time = ch - '0';
                times.add(times.pop() * time);
                bound *= time;
            }

            if (bound >= k) break;
        }

        while (!words.isEmpty()) {
            final long time = times.pop();
            final StringBuilder word = words.pop();

            final long len = bound / time;
            k = ((k - 1L) % len) + 1L;  //[1, len] 1-indexed

            final long nextBound = len - word.length();
            if (k > nextBound) return "" + word.charAt((int)(k - nextBound - 1));
            bound = nextBound;
        }

        return "impossible case";
    }

    public static void main(String[] args) {
        Q880 q880 = new Q880();
        System.out.println(q880.decodeAtIndex("vk6u5xhq9v", 554));
    }
}
