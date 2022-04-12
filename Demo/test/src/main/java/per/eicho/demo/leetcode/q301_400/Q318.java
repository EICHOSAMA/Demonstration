package per.eicho.demo.leetcode.q301_400;

/**
 * <p>318. Maximum Product of Word Lengths 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-product-of-word-lengths/">
 *   318. Maximum Product of Word Lengths</a>
 */
public class Q318 {
    final static int[] tables; // index range [0, 123)

    static {
        final int size = 'z' + 1;
        tables = new int[size];
        for (int i = 'a', temp = 0b1; i <= 'z'; i++, temp <<= 1) {
            tables[i] = temp;
        }
    }

    public int maxProduct(String[] words) {
        // 1. 2 <= words.length <= 1000
        // 2. 1 <= words[i].length <= 1000
        // 3. words[i] consists only of lowercase English letters.        
        final int len = words.length;
        // 1. init table 'useOfLetters'
        final int[] useOfLetters = new int[len];
        for (int i = 0; i < len ; i++) {
            useOfLetters[i] = getUseOfLetter(words[i]);
        }

        // 2. find out result.
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((useOfLetters[i] & useOfLetters[j]) == 0 &&
                     words[i].length() * words[j].length() > result) {
                    result = words[i].length() * words[j].length();
                }
            }
        }
        return result;
    }

    private int getUseOfLetter(String word) {
        //assert word != null;
        int result = 0;
        for (int i = 0; i < word.length(); i++) {
            result |= tables[word.charAt(i)];
        }
        return result;
    }
}
