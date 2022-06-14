package per.eicho.demo.leetcode.q701_800;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>720. Longest Word in Dictionary 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-word-in-dictionary/">
 *   720. Longest Word in Dictionary</a>
 */
public final class Q720 {

    private static final class Trie {

        final char ch;
        boolean isLeaf = false;
        final Map<Character, Trie> children;

        Trie(char ch) {
            this.ch = ch;
            children = new HashMap<>();
        }

        void add(String word) {
            if (word != null && word.length() != 0) add(word, 0);
        }

        private void add(String word, int p) {
            if (p == word.length()) {
                isLeaf = true;
                return;
            }

            final Character c = word.charAt(p);
            if (!children.containsKey(c)) children.put(c, new Trie(c));
            children.get(c).add(word, p + 1);
        }

    }

    private String result = ""; // if there is no answer, return the empty string.

    public String longestWord(String[] words) {
        // 1. 1 <= words.length <= 1000
        // 2. 1 <= words[i].length <= 30
        // 3. words[i] consists of lowercase English letters.
        final Trie root = new Trie('-');
        for (String word : words) root.add(word);

        for (Trie cursor : root.children.values()) {
            if (!cursor.isLeaf) continue;

            final StringBuilder sb = new StringBuilder();
            sb.append(cursor.ch);
            searchLongestWord(cursor, sb);
        }

        return result;
    }

    private void searchLongestWord(Trie node, StringBuilder sb) {
        final int idx = sb.length();
        sb.append(' '); // Placeholder
        for (Trie child : node.children.values()) {
            if (!child.isLeaf) continue;

            sb.setCharAt(idx, child.ch);
            searchLongestWord(child, sb);
        }
        sb.deleteCharAt(idx);

        if (sb.length() > result.length()) {
            result = sb.toString();
        } else if (sb.length() == result.length()){
            final String candidate = sb.toString();
            // return the longest word with the smallest lexicographical order
            result = result.compareTo(candidate) < 0 ? result : candidate;
        }
    }

}
