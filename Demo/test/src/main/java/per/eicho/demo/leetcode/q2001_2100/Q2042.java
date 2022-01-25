package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2042. Check if Numbers Are Ascending in a Sentence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/">
 *   2042. Check if Numbers Are Ascending in a Sentence</a>
 */
public final class Q2042 {
    public boolean areNumbersAscending(String s) {
        // 1. 3 <= s.length <= 200
        // 2. s consists of lowercase English letters, spaces, and digits from 0 to 9, inclusive.
        // 3. The number of tokens in s is between 2 and 100, inclusive.
        // 4. The tokens in s are separated by a single space.
        // 5. There are at least two numbers in s.
        // 6. Each number in s is a positive number less than 100, with no leading zeros.
        // 7. s contains no leading or trailing spaces.
        final String[] tokens = s.split(" ");

        int maxNum = -1;
        for (int i = 0; i < tokens.length; i++) {
            final String token = tokens[i];
            final char firstLetter = token.charAt(0);
            if ('a' <= firstLetter && firstLetter <= 'z') continue;

            int num = Integer.parseInt(token);
            if (num <= maxNum) return false;
            maxNum = num; // case: num > maxNum
        }
        return true;
    }
}
