package per.eicho.demo.datastructure.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>数据结构 - 字典树</p>
 */
public final class Trie {
    
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
