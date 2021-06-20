package per.eicho.demo.leetcode.q501_600;

/**
 * <p>557. Reverse Words in a String III 的题解代码 </p>
 * 
 * <pre>
 *  Given a string s, 
 *  reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 * 
 * Example 1: 
 *    Input: s = "Let's take LeetCode contest"
 *    Output: "s'teL ekat edoCteeL tsetnoc"
 * 
 * Example 2:
 *    Input: s = "God Ding"
 *    Output: "doG gniD"
 * 
 * Constraints:
 *    1. 1 <= s.length <= 5 * 104
 *    2. s contains printable ASCII characters.
 *    3. s does not contain any leading or trailing spaces.
 *    4. There is at least one word in s.
 *    5. All the words in s are separated by a single space.
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string-iii/">557. Reverse Words in a String III</a>
 */
final public class Q557 {

    static final char SPACE = ' ';

    public String reverseWords(String s) {
        final StringBuilder sb = new StringBuilder(s.length());
        final StringBuilder wordSb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            
            if (SPACE == c) {
                sb.append(wordSb.reverse().toString())
                  .append(c);
                wordSb.setLength(0);
                continue;
            }

            wordSb.append(c);
        }

        if (wordSb.length() != 0) {
            sb.append(wordSb.reverse().toString());
        }

        return sb.toString();
    }
}
