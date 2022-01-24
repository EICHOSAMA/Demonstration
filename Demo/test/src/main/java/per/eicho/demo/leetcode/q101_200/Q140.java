package per.eicho.demo.leetcode.q101_200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>140. Word Break II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-break-ii/">140. Word Break II</a>
 */
public final class Q140 {
    private final static class TrieNode {
        boolean isLeaf;
        private Map<Character, TrieNode> children;
        
        TrieNode() {
            children = new HashMap<>(4);
        }
    }

    private final static class Trie {

        private final TrieNode root;

        Trie(List<String> wordDict) {
            root = new TrieNode();

            for (String word : wordDict) {
                appendWord(word);
            }
        }

        void appendWord(String word) {
            // assert word != null;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                final char ch = (char)word.charAt(i);
                final Character character = ch;

                if (node.children.containsKey(character)) {
                    node = node.children.get(character);
                } else {
                    final TrieNode newNode = new TrieNode();
                    node.children.put(character, newNode);
                    node = newNode;
                }

                if (i == word.length() - 1) {
                    node.isLeaf = true;
                }
            }
        }
    }

    private List<String> result;
    private StringBuilder commonSB;

    public List<String> wordBreak(String s, List<String> wordDict) {
        // 1. 1 <= s.length <= 20
        // 2. 1 <= wordDict.length <= 1000
        // 3. 1 <= wordDict[i].length <= 10
        // 4. s and wordDict[i] consist of only lowercase English letters.
        // 5. All the strings of wordDict are unique.

        // 1. build trie.
        final Trie trie = new Trie(wordDict);

        // 2. process
        final Character[] chars = toChars(s); // Java AutoBoxing Performance optimization

        result = new LinkedList<>();
        commonSB = new StringBuilder();
        search(chars, trie, 0, trie.root);

        return result;
    }

    private void search(final Character[] chars, final Trie trie, int index, TrieNode cursor) {
        final Character ch = chars[index];
        if (cursor.children.containsKey(ch)) {
            final TrieNode nextCursor = cursor.children.get(ch);
            commonSB.append(ch);

            if (index + 1 < chars.length) {
                search(chars, trie, index + 1, nextCursor);
                if (nextCursor.isLeaf) {
                    commonSB.append(' ');
                    search(chars, trie, index + 1, trie.root);
                    commonSB.deleteCharAt(commonSB.length() - 1);
                }
            } else {
                if (nextCursor.isLeaf) {
                    result.add(commonSB.toString()); // add to result.
                }
            }
            commonSB.deleteCharAt(commonSB.length() - 1); // cancel for back tracking.
        }
    }

    private Character[] toChars(String s) {
        Character[] characters = new Character[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            characters[i] = chars[i];
        }
        return characters;
    }

    public static void main(String[] args) {
        Q140 q140 = new Q140();
        
        OutputUtils.println(q140.wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
    }
}
