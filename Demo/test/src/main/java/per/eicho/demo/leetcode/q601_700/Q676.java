package per.eicho.demo.leetcode.q601_700;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>676. Implement Magic Dictionary 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-magic-dictionary/">
 *   676. Implement Magic Dictionary</a>
 */
@SuppressWarnings("unused")
public final class Q676 {

    private static final class Trie {

        final char ch;
        final Map<Character, Trie> children;
        boolean isEndOfWord = false;

        Trie(char ch) {
            this.ch = ch;
            children = new HashMap<>();
        }

        void add(String word) {
            if (word != null && word.length() > 0) add(word, 0);
        }

        private void add(String word, int p) {
            if (p >= word.length()) {
                isEndOfWord = true;
                return;
            }
            final Character ch = word.charAt(p);
            if (!children.containsKey(ch)) children.put(ch, new Trie(ch));
            final Trie trie = children.get(ch);
            trie.add(word, p + 1);
        }
    }

    private static final class MagicDictionary {

        private Trie root;

        public MagicDictionary() {
            root = new Trie('0');
        }
        
        public void buildDict(String[] dictionary) {
            // 1. 1 <= dictionary.length <= 100
            // 2. 1 <= dictionary[i].length <= 100
            // 3. dictionary[i] consists of only lower-case English letters.
            // 4. All the strings in dictionary are distinct.
            // 5. buildDict will be called only once before search.
            for (String word : dictionary) root.add(word);
        }
        
        public boolean search(String searchWord) {
            // 1. 1 <= searchWord.length <= 100
            // 2. searchWord consists of only lower-case English letters.
            // 3. At most 100 calls will be made to search.
            return search(searchWord, 0, false, root);
        }

        private boolean search(String word, int p, boolean changed, Trie trie) {
            if (p == word.length()) return trie.isEndOfWord && changed;

            final Character ch = word.charAt(p);
            if (!changed) {
                for (Trie child : trie.children.values()) {
                    if (search(word, p + 1, child.ch != ch, child)) return true;
                }
                return false;
            } else {   
                if (trie.children.containsKey(ch)) return search(word, p + 1, true, trie.children.get(ch));
                return false;
            }
        }
    }
}
