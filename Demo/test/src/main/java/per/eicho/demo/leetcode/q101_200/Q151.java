package per.eicho.demo.leetcode.q101_200;

/**
 * <p>151. Reverse Words in a String 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string/">151. Reverse Words in a String</a>
 */
public final class Q151 {
    public String reverseWords(String s) {
        // 1. 1 <= s.length <= 10^4
        // 2. s contains English letters (upper-case and lower-case), digits, and spaces ' '.
        // 3. There is at least one word in s.

        // Note that s may contain leading or trailing spaces or multiple spaces between two words. 
        // The returned string should only have a single space separating the words. Do not include any extra spaces.
        final String[] words = s.split(" ");
        final StringBuilder sb = new StringBuilder(s.length());
        
        for (int i = words.length - 1; i >= 0; i--) {
            final String word = words[i];
            if (word.length() == 0) continue;
            sb.append(word);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static void main(String[] args) {
        Q151 q151 = new Q151();
        String s = " 123  321     12313  ";
        System.out.println(q151.reverseWords(s));
    }

}
