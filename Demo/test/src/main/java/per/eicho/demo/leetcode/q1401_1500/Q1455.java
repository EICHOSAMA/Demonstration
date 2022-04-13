package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1450. Number of Students Doing Homework at a Given Time 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-students-doing-homework-at-a-given-time/">
 *   1450. Number of Students Doing Homework at a Given Time</a>
 */
public final class Q1455 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        // 1. 1 <= sentence.length <= 100
        // 2. 1 <= searchWord.length <= 10
        // 3. sentence consists of lowercase English letters and spaces.
        // 4. searchWord consists of lowercase English letters.        
        final int n = sentence.length();
        final int m = searchWord.length();

        int idx = 0;
        for (int i = 0; i < n; i++) {
            idx++;

            boolean isPrefixOfWord = true;

            int j = i;
            int k = 0;
            while (j < n && sentence.charAt(j) != ' ') {
                if (isPrefixOfWord && k < m) {
                    if (sentence.charAt(j) != searchWord.charAt(k++)) isPrefixOfWord = false;
                }
                j++;
            }
            i = j;

            if (isPrefixOfWord && k == m) return idx;
        }

        return -1;
    }
}
