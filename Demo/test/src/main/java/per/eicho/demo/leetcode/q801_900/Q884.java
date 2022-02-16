package per.eicho.demo.leetcode.q801_900;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>884. Uncommon Words from Two Sentences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/uncommon-words-from-two-sentences/">884. Uncommon Words from Two Sentences</a>
 */
public final class Q884 {
    public String[] uncommonFromSentences(String s1, String s2) {
        // 1. 1 <= s1.length, s2.length <= 200
        // 2. s1 and s2 consist of lowercase English letters and spaces.
        // 3. s1 and s2 do not have leading or trailing spaces.
        // 4. All the words in s1 and s2 are separated by a single space.            
        final Map<String, Integer> mark = new HashMap<>();

        for (String word : s1.split(" ")) {
            if (!mark.containsKey(word)) {
                mark.put(word, 0b0001);
            } else {
                mark.put(word, 0b0100);
            }
        }

        for (String word : s2.split(" ")) {
            if (!mark.containsKey(word)) {
                mark.put(word, 0b0010);
                continue;
            }

            if ((mark.get(word) & 0b1000) == 0b1000) continue;
            if ((mark.get(word) & 0b0010) == 0b0010) {
                int mk = mark.get(word);
                mk = mk & ~0b0010;
                mk = mk | 0b1000;
                mark.put(word, mark.get(word) - 0b0010 + 0b1000);
            } else {
                mark.put(word, mark.get(word) | 0b0010);
            }
        }

        final List<String> result = new LinkedList<>();
        for (Map.Entry<String, Integer> kvp : mark.entrySet()) {
            if (0b01 == kvp.getValue() || 0b10 == kvp.getValue()) result.add(kvp.getKey());
        }

        return result.toArray(new String[result.size()]);
    }

    public static void main(String[] args) {
        System.out.println(0b0010);
        System.out.println(0b1111 & 0b0010);
        System.out.println(0b1101);
        System.out.println(0b1111 - 0b0010);
        System.out.println(0b1111 & ~0b0010);
        System.out.println(0b1101 & ~0b0010);
        System.out.println(0b0101 | 0b1000);
        
    }
}
