package per.eicho.demo.leetcode.q1001_1100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>1048. Longest String Chain 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-string-chain/">
 *   1048. Longest String Chain</a>
 */
public final class Q1048 {
    public int longestStrChain(String[] words) {
        // 1. 1 <= words.length <= 1000
        // 2. 1 <= words[i].length <= 16
        // 3. words[i] only consists of lowercase English letters.        
        Arrays.sort(words, (w1, w2) -> {
            if (w1.length() != w2.length()) return Integer.compare(w1.length(), w2.length());
            return w1.compareTo(w2);
        });
        final Map<String, Integer> f = new HashMap<>();
        for (String word : words) f.put(word, 1);
        int result = 1;
        for (String word : words) {
            final int len = f.get(word);

            // "abc" ⇒ 0:" abc", 1:"a bc", 2:"ab c", 3:"abc "
            final StringBuilder sb = new StringBuilder(word.length() + 1);
            sb.append(' ');
            sb.append(word);
            for (int i = 0; i <= word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    sb.setCharAt(i, ch);
                    final String str = sb.toString();
                    if (!f.containsKey(str)) continue;
                    f.put(str, Math.max(f.get(str), len + 1));
                }

                if (i < word.length()) sb.setCharAt(i, word.charAt(i));
            }
            result = Math.max(result, len);
        }

        return result;
    }

    public static void main(String[] args) {
        Q1048 q1048 = new Q1048();
        System.out.println(q1048.longestStrChain(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"}));
    }
}
