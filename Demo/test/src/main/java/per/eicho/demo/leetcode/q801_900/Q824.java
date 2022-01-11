package per.eicho.demo.leetcode.q801_900;

/**
 * <p>824. Goat Latin 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/goat-latin/">824. Goat Latin</a>
 */
public final class Q824 {
    public String toGoatLatin(String sentence) {
        final StringBuilder sb = new StringBuilder(sentence.length());

        // 1. sentence to words
        final String[] words = sentence.split(" ");

        int index = 0;
        for (String word : words) {
            index++;

            final char firstChar = (char)word.charAt(0);

            if (isVowel(firstChar)) {
                sb.append(word);
            } else {
                sb.append(word.substring(1));
                sb.append(firstChar);
            }

            sb.append("ma");
            for (int i = 0; i < index; i++) {
                sb.append('a');
            }

            if (index != words.length) sb.append(' ');
        }


        return sb.toString();
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'A' ||
            ch == 'e' || ch == 'E' ||
            ch == 'i' || ch == 'I' ||
            ch == 'o' || ch == 'O' ||
            ch == 'u' || ch == 'U' ;
    }

    public static void main(String[] args) {
        Q824 q824 = new Q824();
        System.out.println(q824.toGoatLatin("I speak Goat Latin"));
    }
}
