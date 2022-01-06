package per.eicho.demo.leetcode.q801_900;

import java.util.HashMap;

/**
 * <p>819. Most Common Word 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/most-common-word/">819. Most Common Word</a>
 */
public final class Q819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        String result = null;
        int time = 0;

        // 1. case-insensitive (answer should be returned in lowercase.)
        paragraph = paragraph.toLowerCase();

        // 2. split into words
        String[] words = paragraph.split("\\W+");
        
        // HashMap<Word, Count|Banned>
        // value = -1 means the word is banned
        // value >= 0 means the word is not banned and the value is the apperance time of the spefic word.
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        for (String bannedWord : banned) {
            hashMap.put(bannedWord, -1);
        }

        for (String word : words) {
            Integer val = hashMap.get(word);

            if (val == null) val = 0;
            if (val == -1) continue;

            int newVal = val + 1;
            if (newVal > time) {
                time = newVal;
                result = word;
            }
            hashMap.put(word, newVal);
        }

        return result;
    }

    public static void main(String[] args) {
        Q819 q819 = new Q819();
        final String result = q819.mostCommonWord("a.", new String[]{"hit"});
        System.out.println(result);
    }
}
