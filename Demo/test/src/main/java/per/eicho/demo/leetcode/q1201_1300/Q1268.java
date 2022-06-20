package per.eicho.demo.leetcode.q1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>1268. Search Suggestions System 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/search-suggestions-system/">
 *   1268. Search Suggestions System</a>
 */
public final class Q1268 {

    private static final class Trie {

        final Map<Character, Trie> children;
        final List<String> suggestions; 

        Trie () {
            children = new HashMap<>();
            suggestions = new ArrayList<>(3);
        }

        public void addWord(String word) {
            // 1 <= word.length <= 3000
            addWord(word, 0);
        }

        private void addWord(String word, int p) {
            if (suggestions.size() < 3) suggestions.add(word);
            if (p == word.length()) return;

            final char ch = word.charAt(p);
            if (!children.containsKey(ch)) children.put(ch, new Trie());
            
            children.get(ch).addWord(word, p + 1);
        }

        private static List<List<String>> getSuggestions(String word, Trie root) {
            final int n = word.length();
            List<List<String>> result = new ArrayList<>(n);
            Trie cursor = root;

            for (int i = 0; i < n; i++) {
                final char ch = word.charAt(i);

                if (cursor == null) {
                    result.add(new ArrayList<>());
                } else if (!cursor.children.containsKey(ch)) {
                    cursor = null;
                    result.add(new ArrayList<>());
                } else {
                    cursor = cursor.children.get(ch);
                    result.add(cursor.suggestions);
                }
            }
            return result;
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // 1. 1 <= products.length <= 1000
        // 2. 1 <= products[i].length <= 3000
        // 3. 1 <= sum(products[i].length) <= 2 * 10^4
        // 4. All the strings of products are unique.
        // 5. products[i] consists of lowercase English letters.
        // 6. 1 <= searchWord.length <= 1000
        // 7. searchWord consists of lowercase English letters.
        Arrays.sort(products);
        final Trie root = new Trie();
        for (String product : products) root.addWord(product);
        return Trie.getSuggestions(searchWord, root);
    }
}
