package per.eicho.demo.leetcode.q1101_1200;

import java.util.Arrays;

/**
 * <p>1160. Find Words That Can Be Formed by Characters 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/">1160. Find Words That Can Be Formed by Characters</a>
 */
public final class Q1160 {
    public int countCharacters(String[] words, String chars) {
        // 1. 1 <= words.length <= 1000
        // 2. 1 <= words[i].length, chars.length <= 100
        // 3. words[i] and chars consist of lowercase English letters.
        final int[] count = new int[26];
        fill(count, chars);

        int result = 0;
        final int[] countOfWord = new int[26];
        for (String word : words) {
            fill(countOfWord, word);

            boolean goodString = true;
            for (int i = 0; i < count.length; i++) {
                if (countOfWord[i] > count[i]) {
                    goodString = false;
                    break;
                }
            }

            if (goodString) result += word.length();
        }

        return result;
    }

    private void fill(int[] count, String word) {
        Arrays.fill(count, 0);
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i) - 'a']++;
        }
    }
}
