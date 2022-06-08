package per.eicho.demo.leetcode.q601_700;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>648. Replace Words 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/replace-words/">
 *   648. Replace Words</a>
 */
public final class Q648 {

    private static final class Trie {

        boolean isEndOfWord = false;
        final Map<Character, Trie> children;
        
        Trie() {
            children = new HashMap<>();
        }

        void addWord(String word) { 
            if (word == null || word.length() == 0) return;
            addWord(word, 0); 
        }

        private void addWord(String word, int p) {
            final Character ch = word.charAt(p);
            if (!children.containsKey(ch)) children.put(ch, new Trie());
            final Trie child = children.get(ch);

            if (p + 1 == word.length()) {
                child.isEndOfWord = true;
            } else {
                child.addWord(word, p + 1);
            }
        }

        private boolean canMove(char ch) { 
            if (isEndOfWord) return false;
            return children.containsKey(ch); 
        }
        private Trie move(char ch) { return children.get(ch); }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        // 1. 1 <= dictionary.length <= 1000
        // 2. 1 <= dictionary[i].length <= 100
        // 3. dictionary[i] consists of only lower-case letters.
        // 4. 1 <= sentence.length <= 106
        // 5. sentence consists of only lower-case letters and spaces.
        // 6. The number of words in sentence is in the range [1, 1000]
        // 7. The length of each word in sentence is in the range [1, 1000]
        // 8. Every two consecutive words in sentence will be separated by exactly one space.
        // 9. sentence does not have leading or trailing spaces.
        final Trie root = new Trie(); 
        for (String word : dictionary) root.addWord(word);

        final StringBuilder resultSb = new StringBuilder();

        final int len = sentence.length();
        for (int i = 0; i < len; i++) {
            char ch;
            Trie cursor = root;
            
            boolean stopMove = false;
            while (i < len && (ch = sentence.charAt(i)) != ' ') {
                i++;

                if (!stopMove) {
                    if (cursor.canMove(ch)) {
                        cursor = cursor.move(ch);
                        resultSb.append(ch);
                    } else {
                        // case1. cursor.isEndOfWord = true
                        // case2. cursor.children.containsKey(ch) = false
                        if (!cursor.isEndOfWord) resultSb.append(ch);
                        stopMove = true;
                    }
                } else if (!cursor.isEndOfWord){
                    resultSb.append(ch);
                }
            }
            if (i < len) resultSb.append(' ');
        }
        return resultSb.toString();
    }
}
