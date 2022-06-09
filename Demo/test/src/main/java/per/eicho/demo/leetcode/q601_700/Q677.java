package per.eicho.demo.leetcode.q601_700;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>677. Map Sum Pairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/map-sum-pairs/">
 *   677. Map Sum Pairs</a>
 */
@SuppressWarnings("unused")
public final class Q677 {

    private static final class Trie {

        final char ch;
        final Map<Character, Trie> children;
        int sum = 0;
        int val = 0;

        Trie(char ch) {
            this.ch = ch;
            children = new HashMap<>();
        }

        void add(String word, int val) {
            if (word != null && word.length() > 0) add(word, 0, val);
        }

        private int add(String word, int p, int val) {
            if (p >= word.length()) {
                final int diff = val - this.val;
                this.val = val;
                return diff;
            }
            final Character ch = word.charAt(p);
            if (!children.containsKey(ch)) children.put(ch, new Trie(ch));
            final Trie trie = children.get(ch);
            final int diff = trie.add(word, p + 1, val);
            sum += diff;
            return diff;
        }
    }

    private static final class MapSum {

        final Trie root;

        public MapSum() {
            root = new Trie('0');
        }
        
        public void insert(String key, int val) {
            // 1. 1 <= key.length, prefix.length <= 50
            // 2. key and prefix consist of only lowercase English letters.
            // 3. 1 <= val <= 1000
            // 4. At most 50 calls will be made to insert and sum.
            root.add(key, val);
        }
        
        public int sum(String prefix) {
            // 1. At most 50 calls will be made to insert and sum.
            Trie cursor = root;
            int p = 0;
            while (p < prefix.length()) {
                final Character ch = prefix.charAt(p++);
                if (!cursor.children.containsKey(ch)) return 0;
                cursor = cursor.children.get(ch);
            }
            return cursor.sum + cursor.val;
        }
    }
}
