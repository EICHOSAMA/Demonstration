package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>500. Keyboard Row 的题解代码 </p>
 * 
 * <pre>
 *  Given an array of strings words, return the words that can be typed using 
 *  letters of the alphabet on only one row of American keyboard like the image below.
 * 
 *  In the American keyboard:
 *   1. the first row consists of the characters "qwertyuiop",
 *   2. the second row consists of the characters "asdfghjkl", and
 *   3. the third row consists of the characters "zxcvbnm".
 * 
 * Example 1:
 *    Input: words = ["Hello","Alaska","Dad","Peace"]
 *    Output: ["Alaska","Dad"]
 * 
 * Example 2:
 *    Input: words = ["omk"]
 *    Output: []
 *  
 * Example 3:
 *    Input: words = ["adsdf","sfd"]
 *    Output: ["adsdf","sfd"]
 * 
 * Constraints:
 *   1. 1 <= words.length <= 20
 *   2. 1 <= words[i].length <= 100
 *   3. words[i] consists of English letters (both lowercase and uppercase). 
 * </pre>
 * @see <a href="https://leetcode.com/problems/keyboard-row/">500. Keyboard Row</a>
 */
final public class Q500 {
    // key = char - 'a'
    private final static int[] KEY_ROW_MAPPING;
    static {
        final char offset = 'a';
        KEY_ROW_MAPPING = new int[26];

        
        KEY_ROW_MAPPING['a' - offset] = 2;
        KEY_ROW_MAPPING['b' - offset] = 3;
        KEY_ROW_MAPPING['c' - offset] = 3;
        KEY_ROW_MAPPING['d' - offset] = 2;
        KEY_ROW_MAPPING['e' - offset] = 1;
        KEY_ROW_MAPPING['f' - offset] = 2;
        KEY_ROW_MAPPING['g' - offset] = 2;
        KEY_ROW_MAPPING['h' - offset] = 2;
        KEY_ROW_MAPPING['i' - offset] = 1;
        KEY_ROW_MAPPING['j' - offset] = 2;
        KEY_ROW_MAPPING['k' - offset] = 2;
        KEY_ROW_MAPPING['l' - offset] = 2;
        KEY_ROW_MAPPING['m' - offset] = 3;
        KEY_ROW_MAPPING['n' - offset] = 3;
        KEY_ROW_MAPPING['o' - offset] = 1;
        KEY_ROW_MAPPING['p' - offset] = 1;
        KEY_ROW_MAPPING['q' - offset] = 1;
        KEY_ROW_MAPPING['r' - offset] = 1;
        KEY_ROW_MAPPING['s' - offset] = 2;
        KEY_ROW_MAPPING['t' - offset] = 1;
        KEY_ROW_MAPPING['u' - offset] = 1;
        KEY_ROW_MAPPING['v' - offset] = 3;
        KEY_ROW_MAPPING['w' - offset] = 1;
        KEY_ROW_MAPPING['x' - offset] = 3;
        KEY_ROW_MAPPING['y' - offset] = 1;
        KEY_ROW_MAPPING['z' - offset] = 3;
        
    }

    public String[] findWords(String[] words) {
        final List<String> result = new ArrayList<String>();

        for (String word: words) {
            if (validWord(word)) {
                result.add(word);
            }
        }

        return result.stream().toArray(String[]::new);
    }

    private boolean validWord(String word) {
        if (word.length() == 1) {
            return true;
        }
        word = word.toLowerCase();

        final int row = KEY_ROW_MAPPING[word.charAt(0) - 'a'];

        for (char c: word.toCharArray()) {
            if (KEY_ROW_MAPPING[c - 'a'] != row) {
                return false;
            }
        }
        return true;
    }
}
