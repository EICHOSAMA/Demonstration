package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>745. Prefix and Suffix Search 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/prefix-and-suffix-search/">
 *   745. Prefix and Suffix Search</a>
 */
public final class Q745 {
    private static final class WordFilter {

        private static final class Trie {
        
            private final List<Integer> prefixIndexes;
            private final List<Integer> suffixIndexes;
            private final Map<Character, Trie> children; 
    
            Trie() {
                prefixIndexes = new ArrayList<>();
                suffixIndexes = new ArrayList<>();
                children = new HashMap<>();
            }
    
            private void addWord(String word, Integer idx) {
                addWordP(word, idx, 0);
                addWordS(word, idx, word.length() - 1);
            }
    
            private void addWordP(String word, Integer idx, int p) {
                // prefix
                if (p == word.length()) return;
                final char ch = word.charAt(p);
                if (!children.containsKey(ch)) children.put(ch, new Trie());
    
                final Trie child = children.get(ch);
                child.prefixIndexes.add(idx);
                child.addWordP(word, idx, p + 1);
            }
    
            private void addWordS(String word, Integer idx, int p) {
                // suffix
                if (p < 0) return;
                final char ch = word.charAt(p);
                if (!children.containsKey(ch)) children.put(ch, new Trie());
                final Trie child = children.get(ch);
                child.suffixIndexes.add(idx);
                child.addWordS(word, idx, p - 1);                    
            }
    
            public List<Integer> searchWordP(String prefix) { return searchWordP(prefix, 0); }
            public List<Integer> searchWordS(String suffix) { return searchWordS(suffix, suffix.length() - 1); }
            
            private List<Integer> searchWordP(String word, int p) {
                if (p == word.length()) return prefixIndexes;
                final char ch = word.charAt(p);
                if (!children.containsKey(ch)) return null;
                return children.get(ch).searchWordP(word, p + 1);
            }
            
            private List<Integer> searchWordS(String word, int p) {
                if (p < 0) return suffixIndexes;
                final char ch = word.charAt(p);
                if (!children.containsKey(ch)) return null;                
                return children.get(ch).searchWordS(word, p - 1);
            }
        }        

        final Trie root;

        /** Initializes the object with the words in the dictionary. */
        public WordFilter(String[] words) {
            root = new Trie();
            for (int i = 0; i < words.length; i++) {
                final String word = words[i];
                root.addWord(word, i);
            }
        }
        
        /** 
         * Returns the index of the word in the dictionary, 
         * which has the prefix prefix and the suffix suffix. 
         * If there is more than one valid index, return the largest of them. 
         * If there is no such word in the dictionary, return -1. 
         */
        public int f(String prefix, String suffix) {
            final List<Integer> prefixIndexes = root.searchWordP(prefix);
            if (prefixIndexes == null) return -1;
            final List<Integer> suffixIndexes = root.searchWordS(suffix);
            if (suffixIndexes == null) return -1;

            int p1 = prefixIndexes.size() - 1;
            int p2 = suffixIndexes.size() - 1;
            while (p1 >= 0 && p2 >= 0) {
                final int idxP = prefixIndexes.get(p1);
                int idxS = 0;
                while (p2 >= 0 && idxP < (idxS = suffixIndexes.get(p2))) p2--;
                if (idxP == idxS) return idxP;
                p1--;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        WordFilter q745 = new WordFilter(new String[]{"cabaabaaaa","ccbcababac","bacaabccba","bcbbcbacaa","abcaccbcaa","accabaccaa","cabcbbbcca","ababccabcb","caccbbcbab","bccbacbcba"});
        System.out.println(q745.f("ab", "abcaccbcaa"));
        // ["WordFilter","f","f","f","f","f","f","f","f","f","f"]
        // [[["cabaabaaaa","ccbcababac","bacaabccba","bcbbcbacaa","abcaccbcaa","accabaccaa","cabcbbbcca","ababccabcb","caccbbcbab","bccbacbcba"]],
        // ["bccbacbcba","a"],["ab","abcaccbcaa"],["a","aa"],["cabaaba","abaaaa"],["cacc","accbbcbab"],["ccbcab","bac"],["bac","cba"],["ac","accabaccaa"],["bcbb","aa"],["ccbca","cbcababac"]]
    }
}
