package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * <p>212. Word Search II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-search-ii/">212. Word Search II</a>
 */
public final class Q212 {

    /**
     * <p>字典树节点内部类</p>
     */
    private static class TrieNode {
        final Map<Character, TrieNode> children;

        boolean isLeaf;

        TrieNode() {
            children = new HashMap<>();
            isLeaf = false;
        }

        boolean containsKey(Character key) {
            return children.containsKey(key);
        }

        void add(Character key) {
            children.put(key, new TrieNode());
        }

        TrieNode get(Character key) {
            return children.get(key);
        }

        void markAsLeaf() {
            isLeaf = true;
        }

        boolean isLeaf() {
            return isLeaf;
        }
        
    }

    /**
     * <p>数据结构 - 字典树</p>
     */
    private static final class Trie {
        
        final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public Trie(String[] words) {
            this();
            for (String word : words) {
                addWord(word);
            }
        }

        /**
         * <p>向字典树里添加一个单词</p>
         * 
         * <pre>
         *   如果单词为 null 或是 空 将不进行任何处理，
         *   大小写会被无视。(case ignore)
         * </pre>
         * 
         * @param word 指定的单词
         */
        public void addWord(String word) {
            if (word == null || word.length() == 0) return;
            word = word.toLowerCase();

            TrieNode cursor = root;
            for (int i = 0; i < word.length(); i++) {
                Character ch = word.charAt(i);
                
                if (!cursor.containsKey(ch)) {
                    cursor.add(ch);
                }
                cursor = cursor.get(ch);
            }
            cursor.markAsLeaf();
        }

        /**
         * <p>在字典树里寻找指定单词是否存在</p>
         * 
         * <pre>
         *   如果单词为 null 或是 空 将不进行任何处理，
         *   大小写会被无视。(case ignore)
         * </pre>
         * 
         * @param word 指定的单词
         * @return true 如果在字典树中存在该单词。
         */
        @SuppressWarnings("unused")
        public boolean find(String word) {
            if (word == null || word.length() == 0) return false;
            word = word.toLowerCase();
            TrieNode cursor = root;
            for (int i = 0; i < word.length(); i++) {
                Character ch = word.charAt(i);
                
                if (!cursor.containsKey(ch)) return false;
                cursor = cursor.get(ch);
            }
            return cursor.isLeaf();
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        // 1. m == board.length
        // 2. n == board[i].length
        // 3. 1 <= m, n <= 12
        // 4. board[i][j] is a lowercase English letter.
        // 5. 1 <= words.length <= 3 * 10^4
        // 6. 1 <= words[i].length <= 10
        // 7. words[i] consists of lowercase English letters.
        // 8. All the strings of words are unique.
        final Trie trie = new Trie(words);
        final int m = board.length;
        final int n = board[0].length;
        final boolean[][] marks = new boolean[m][n];
        final Stack<TrieNode> stack = new Stack<>();
        final Set<String> result = new HashSet<>();
        final StringBuilder sb = new StringBuilder();

        stack.add(trie.root);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                search(board, trie, marks, stack, result, sb, i, j);
            }
        }

        return new ArrayList<>(result);
    }

    private void search(
        char[][] board, Trie trie, boolean[][] marks, 
        Stack<TrieNode> stack, Set<String> result, StringBuilder sb,
        int x, int y) {
        if (x < 0 || x >= board.length) return;
        if (y < 0 || y >= board[0].length) return;
        if (marks[x][y] == true) return;
        
        final TrieNode currentNode = stack.peek();
        final Character ch = board[x][y];

        if (!currentNode.containsKey(ch)) return;
        
        // mark
        stack.add(currentNode.get(ch));
        sb.append(ch);
        marks[x][y] = true;

        if (stack.peek().isLeaf()) {
            result.add(sb.toString());
        }

        // 1. left
        search(board, trie, marks, stack, result, sb, x, y - 1);

        // 2. right
        search(board, trie, marks, stack, result, sb, x, y + 1);

        // 3. top
        search(board, trie, marks, stack, result, sb, x - 1, y);

        // 4. bottom
        search(board, trie, marks, stack, result, sb, x + 1, y);

        marks[x][y] = false;
        stack.pop();
        sb.deleteCharAt(sb.length() - 1);
    }
}
