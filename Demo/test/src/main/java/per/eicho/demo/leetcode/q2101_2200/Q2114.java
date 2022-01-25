package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2114. Maximum Number of Words Found in Sentences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-number-of-words-found-in-sentences/">
 *   2114. Maximum Number of Words Found in Sentences</a>
 */
public final class Q2114 {
    public int mostWordsFound(String[] sentences) {
        // 1. 1 <= sentences.length <= 100
        // 2. 1 <= sentences[i].length <= 100
        // 3. sentences[i] consists only of lowercase English letters and ' ' only.
        // 4. sentences[i] does not have leading or trailing spaces.
        // 5. All the words in sentences[i] are separated by a single space.
        int result = 0;
        for (String sentence : sentences) {
            int count = 1;
            for (int i = 0; i < sentence.length(); i++) {
                if (sentence.charAt(i) == ' ') {
                    count++;
                }
            }
            result = Math.max(result, count);
        }
        return result;
    }
}
