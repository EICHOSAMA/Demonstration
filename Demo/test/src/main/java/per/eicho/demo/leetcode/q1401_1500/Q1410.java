package per.eicho.demo.leetcode.q1401_1500;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>1410. HTML Entity Parser 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/html-entity-parser/">
 *   1410. HTML Entity Parser</a>
 */
public final class Q1410 {

    private static final class Trie {
        
        final Map<Character, Trie> children;
        Character toChar = null;
        
        Trie() {
            children = new HashMap<>();
        }

        void add(String word, Character toChar) {
            add(word, 0, toChar);
        }

        void add(String word, int p, Character toChar) {
            if (p == word.length()) {
                this.toChar = toChar;
                return;
            }

            final char ch = word.charAt(p);
            if (!children.containsKey(ch)) children.put(ch, new Trie());
            children.get(ch).add(word, p + 1, toChar);
        }

    }

    private static final String[] fromStrs = new String[]{
        "&quot;", "&apos;", "&amp;", "&gt;", "&lt;", "&frasl;"};
    private static final char[] toChars = new char[]{
        '"', '\'', '&', '>', '<', '/'
    };

    public String entityParser(String text) {
        // 1. 1 <= text.length <= 105
        // 2. The string may contain any 
        //    possible characters out of all the 256 ASCII characters.
        // &quot; -> "
        // &apos; -> '
        // &amp;  -> &
        // &gt;   -> >
        // &lt;   -> <
        // &frasl;-> /        
        final int n = text.length();

        final Trie root = new Trie();
        for (int i = 0; i < fromStrs.length; i++) {
            root.add(fromStrs[i], toChars[i]);
        }
        
        final StringBuilder sb = new StringBuilder();
        final Deque<Character> deque = new LinkedList<>();
        Trie cursor = root;
        for (int i = 0; i < n; i++) {
            final char ch = text.charAt(i);

            if (cursor.children.containsKey(ch)) {
                deque.addLast(ch);
                cursor = cursor.children.get(ch);
                if (cursor.toChar != null) {
                    sb.append(cursor.toChar);
                    deque.clear();
                    cursor = root;
                }
                continue;
            }

            if (!deque.isEmpty()) {
                while (!deque.isEmpty()) sb.append(deque.pollFirst());
                i--;
                cursor = root;
            } else {
                sb.append(ch);
            }
        }

        while (!deque.isEmpty()) sb.append(deque.pollFirst());

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println('/');
        System.out.println('&');
        System.out.println('\'');
    }
}
