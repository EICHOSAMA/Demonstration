package per.eicho.demo.leetcode.q801_900;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>820. Short Encoding of Words 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/short-encoding-of-words/">
 *   820. Short Encoding of Words</a>
 */
public final class Q820 {

    private static final class Trie {
        final Map<Character, Trie> children;
        final int layer; // = len, 0-based. root.layer = 0;
        boolean isLeaf = false;

        Trie(int layer) {
            children = new HashMap<>();
            this.layer = layer;
        }

        public int addWord(String word) { return addWord(word, word.length() - 1); }

        private int addWord(String word, int p) {
            if (p < 0) {
                if (isLeaf) return 0;
                isLeaf = children.size() == 0;
                return isLeaf ? this.layer + 1 : 0;
            }

            int result = 0;

            final char ch = word.charAt(p);
            if (!children.containsKey(ch)) {
                children.put(ch, new Trie(layer + 1));
                
                if (isLeaf) {
                    isLeaf = false;
                    result -= (layer + 1);
                }
            }

            final Trie child = children.get(ch);
            result += child.addWord(word, p - 1);
            return result;
        }
    }

    public int minimumLengthEncoding(String[] words) {
        // 1. 1 <= words.length <= 2000
        // 2. 1 <= words[i].length <= 7
        // 3. words[i] consists of only lowercase letters.
        int result = 0;
        final Trie root = new Trie(0);
        for (String word : words) result += root.addWord(word);
        return result;
    }

    public static void main(String[] args) {
        Q820 q820 = new Q820();
        System.out.println(q820.minimumLengthEncoding(new String[]{"time", "me", "bell"}));
        System.out.println(q820.minimumLengthEncoding(new String[]{"me", "time"}));
        System.out.println(q820.minimumLengthEncoding(new String[]{"feipyxx","e"}));
        System.out.println(q820.minimumLengthEncoding(new String[]{"time","time","time","time"}));
        
    }
}
