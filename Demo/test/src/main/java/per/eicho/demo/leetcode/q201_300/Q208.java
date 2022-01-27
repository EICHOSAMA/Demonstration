package per.eicho.demo.leetcode.q201_300;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>208. Implement Trie (Prefix Tree) 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-trie-prefix-tree/">208. Implement Trie (Prefix Tree)</a>
 */
@SuppressWarnings("unused")
public final class Q208 {
    private static final class Trie {

        private static final class TrieNode {

            final char ch;
            boolean wordMark;
            Map<Character, TrieNode> children;

            TrieNode(char ch) {
                this.ch = ch;
                children = new HashMap<>(4);
            }
        }

        final TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode('0');
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            // 1 <= word.length, prefix.length <= 2000
            //if (word == null || word.length() == 0) return;
            
            TrieNode cursor = root;
            for (int i = 0; i < word.length(); i++) {
                final Character ch = word.charAt(i);

                if (!cursor.children.containsKey(ch)) {
                    final TrieNode newNode = new TrieNode(ch);
                    cursor.children.put(ch, newNode);
                    cursor = newNode;
                } else {
                    cursor = cursor.children.get(ch);
                }
            }
            cursor.wordMark = true;
        }
        
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            // 1 <= word.length, prefix.length <= 2000
            // if (word == null || word.length() == 0) return false;

            TrieNode cursor = root;
            for (int i = 0; i < word.length(); i++) {
                final Character ch = word.charAt(i);

                if (!cursor.children.containsKey(ch)) return false;
                cursor = cursor.children.get(ch);
            }
            return cursor.wordMark;
        }
        
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            // 1 <= word.length, prefix.length <= 2000
            // if (prefix == null || prefix.length() == 0) return false;
            TrieNode cursor = root;

            for (int i = 0; i < prefix.length(); i++) {
                final Character ch = prefix.charAt(i);

                if (!cursor.children.containsKey(ch)) return false;
                cursor = cursor.children.get(ch);
            }

            return true;
        }
    }
}
