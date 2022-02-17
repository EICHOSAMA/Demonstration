package per.eicho.demo.leetcode.q1001_1100;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>1078. Occurrences After Bigram 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/occurrences-after-bigram/">
 *   1078. Occurrences After Bigram</a>
 */
public final class Q1078 {
    public String[] findOcurrences(String text, String first, String second) {
        // 1. 1 <= text.length <= 1000
        // 2. text consists of lowercase English letters and spaces.
        // 3. All the words in text a separated by a single space.
        // 4. 1 <= first.length, second.length <= 10
        // 5. first and second consist of lowercase English letters.        
        final List<String> result = new LinkedList<>();

        final String[] words = text.split(" ");

        for (int i = 0; i < words.length - 2; i++) {
            if (!words[i].equals(first)) continue;
            if (!words[i + 1].equals(second)) continue;
            result.add(words[i + 2]);
        }

        int p = 0;
        final String[] resultArr = new String[result.size()];
        for (String word : result) {
            resultArr[p++] = word;
        }

        return resultArr;
    }
}
