package per.eicho.demo.leetcode.q801_900;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>890. Find and Replace Pattern 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-and-replace-pattern/">
 *   890. Find and Replace Pattern</a>
 */
public class Q890 {
    private static final class Trie {

        final Map<Character, Trie> children;
        String ref;
        int count;

        Trie() {
            children = new HashMap<>();
            count = 0;
        }

        void addWord(String word) {
            addWord(word, 0); 
        }

        private void addWord(String word, int p) {
            if (p == word.length()) {
                ref = word; // isLeaf
                count++;
                return;
            }

            final char ch = word.charAt(p);
            if (!children.containsKey(ch)) children.put(ch, new Trie());
            children.get(ch).addWord(word, p + 1);
        }
    }

    List<String> result;
    Map<Character, Character> from2ToMapping;
    Map<Character, Character> to2FromMapping;
    

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        // 1. 1 <= pattern.length <= 20
        // 2. 1 <= words.length <= 50
        // 3. words[i].length == pattern.length
        // 4. pattern and words[i] are lowercase English letters.
        final Trie root = new Trie();
        for (String word : words) root.addWord(word);
        result = new LinkedList<>();
        from2ToMapping = new HashMap<>();
        to2FromMapping = new HashMap<>();
        search(root, pattern, 0);
        return result;
    }

    private void search(Trie node, String pattern, int p) {
        if (p == pattern.length()) {
            for (int i = 0; i < node.count; i++) result.add(node.ref);
            return;
        }
        
        final Character from = pattern.charAt(p);
        for (Map.Entry<Character, Trie> entry : node.children.entrySet()) {
            final Character to = entry.getKey();
            final Trie child = entry.getValue();

            if (from2ToMapping.containsKey(from)) {
                if (!from2ToMapping.get(from).equals(to)) continue;
                search(child, pattern, p + 1);
                continue;
            }

            if (to2FromMapping.containsKey(to)) continue; // already been used.

            from2ToMapping.put(from, to);
            to2FromMapping.put(to, from);
            search(child, pattern, p + 1);
            from2ToMapping.remove(from);
            to2FromMapping.remove(to);       
        } 
    }

    public static void main(String[] args) {
        Q890 q890 = new Q890();
        OutputUtils.println(q890.findAndReplacePattern(new String[]{"t","g","k","n", "k"}, "v"));
    }
}
