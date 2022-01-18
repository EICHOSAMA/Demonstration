package per.eicho.demo.leetcode.q201_300;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>290. Word Pattern 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-pattern/">290. Word Pattern</a>
 */
public final class Q290 {
    public boolean wordPattern(String pattern, String str) {
        final int count = pattern.length();
        final String[] words = str.split(" ");

        if (count != words.length) return false;

        final Map<Character, String> mappingTable = new HashMap<>();
        for (int i = 0; i < count; i++) {
            final Character ch = pattern.charAt(i);
            final String word = words[i];
            
            if (!mappingTable.containsKey(ch)) {
                if (mappingTable.containsValue(word)) return false;
                // register word with key ch.
                mappingTable.put(ch, word);
                continue;
            } 
            
            if (!mappingTable.get(ch).equals(word)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Q290 q290 = new Q290();
        System.out.println(q290.wordPattern("abba", "dog dog dog dog"));
    }
}
