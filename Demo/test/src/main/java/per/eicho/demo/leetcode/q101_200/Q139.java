package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>139. Word Break 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-break/">139. Word Break</a>
 */
public final class Q139 {

    private final static class TrieNode {

        boolean isLeaf;
        private HashMap<Character, TrieNode> children;

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

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

        final Trie trie = new Trie(wordDict);

        for (int i = 0; i < s.length(); i++) {
            if (f[i] != true) continue;

            TrieNode node = trie.root;
            int cursor = i; // [0, s.length)

            while (cursor < s.length()) {
                char ch = s.charAt(cursor);
                Character character = ch;
    
                if (!node.children.containsKey(character)) {
                    break;
                }

                node = node.children.get(character);
                if (node.isLeaf) {
                    f[cursor + 1] = true;
                }
                cursor++;
            }

        }

        return f[s.length()];
    }

    public static void main(String[] args) {
        Q139 q139 = new Q139();
        String input1 = "cars";
        List<String> input2 = new ArrayList<>();
        input2.add("car");
        input2.add("ca");
        input2.add("rs");
        

        System.out.println(q139.wordBreak(input1, input2));
    }
}
