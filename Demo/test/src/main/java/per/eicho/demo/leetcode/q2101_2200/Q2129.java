package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2129. Capitalize the Title 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/capitalize-the-title/">
 *   2129. Capitalize the Title</a>
 */
public final class Q2129 {
    final static int OFFSET = 'A' - 'a';

    public String capitalizeTitle(String title) {
        title = title.toLowerCase();
        final StringBuilder sb = new StringBuilder();
        final String[] words = title.split(" ");

        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (word.length() < 3) {
                sb.append(word);
            } else {
                sb.append((char)(word.charAt(0) + OFFSET));
                sb.append(word, 1, word.length());
            }
            
            sb.append(' ');
        }

        sb.deleteCharAt(sb.length() - 1); // remove last space

        return sb.toString();
    }
}
