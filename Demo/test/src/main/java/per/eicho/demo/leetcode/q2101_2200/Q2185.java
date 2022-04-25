package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2180. Count Integers With Even Digit Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-integers-with-even-digit-sum/">
 *   2180. Count Integers With Even Digit Sum</a>
 */
public final class Q2185 {
    public int prefixCount(String[] words, String pref) {
        // 1. 1 <= words.length <= 100
        // 2. 1 <= words[i].length, pref.length <= 100
        // 3. words[i] and pref consist of lowercase English letters.        
        int count = 0;
        for (String word : words) {
            if (word.length() < pref.length()) continue;

            int i = 0;
            int j = 0;
            while (j < pref.length()) {
                if (word.charAt(i) != pref.charAt(j)) break;
                i++; j++;
            }

            if (j == pref.length()) count++;
        }

        return count;
    }
}
