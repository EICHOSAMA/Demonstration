package per.eicho.demo.leetcode.q201_300;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>211. Design Add and Search Words Data Structure 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-add-and-search-words-data-structure/">211. Design Add and Search Words Data Structure</a>
 */
public final class Q211 {
    private static final class WordDictionary {

        private static final class TrieNode {

            final Map<Character, TrieNode> children;
            boolean wordMark;

            TrieNode() {
                children = new HashMap<>(2);
                wordMark = false;
            }

        }

        final TrieNode root;

        /** Initializes the object. */
        public WordDictionary() {
            root = new TrieNode();
        }
        
        /** Adds word to the data structure, it can be matched later. */
        public void addWord(String word) {
            // 1. 1 <= word.length <= 500
            // 2. word in addWord consists lower-case English letters.

            TrieNode cursor = root;
            for (int i = 0; i < word.length(); i++) {
                final Character ch = word.charAt(i);

                if (!cursor.children.containsKey(ch)) {
                    final TrieNode nextNode = new TrieNode();
                    cursor.children.put(ch, nextNode);
                    cursor = nextNode;
                } else {
                    cursor = cursor.children.get(ch);
                }
            }
            cursor.wordMark = true;
        }
        
        /** 
         * Returns true if there is any string in the data structure that matches word or false otherwise. 
         * word may contain dots '.' where dots can be matched with any letter 
         */
        public boolean search(String word) {
            // 1. 1 <= word.length <= 500
            // 2. word in search consist of  '.' or lower-case English letters.
            return doSearch(word, 0, root);
        }

        private boolean doSearch(String word, int idx, TrieNode cursor) {
            if (idx == word.length()) {
                return cursor.wordMark;
            }

            final char ch = word.charAt(idx);

            if (ch != '.') {
                final Character character = ch;
                if (!cursor.children.containsKey(character)) return false;
                return doSearch(word, idx + 1, cursor.children.get(character));
            }

            for (TrieNode childNode : cursor.children.values()) {
                if (doSearch(word, idx + 1, childNode)) return true; // any match then return true.
            }
            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

        wordDictionary.addWord("word");
        System.out.println(wordDictionary.search("wo.."));
    }
}
