package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>792. Number of Matching Subsequences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-matching-subsequences/">
 *   792. Number of Matching Subsequences</a>
 */
public final class Q792 {

    private static final class Node {
        final String word;
        int idx;

        Node(String word) {
            this.word = word;
            idx = 0;
        }

        boolean move() {
            return ++idx == word.length();
        }
        
        char current() {
            return word.charAt(idx);
        }
    }

    public int numMatchingSubseq(String s, String[] words) {
        // 1. 1 <= s.length <= 5 * 10^4
        // 2. 1 <= words.length <= 5000
        // 3. 1 <= words[i].length <= 50
        // 4. s and words[i] consist of only lowercase English letters.        
        int result = 0;
        List<List<Node>> heads = new ArrayList<>(26);

        for (int i = 0; i < 26; i++) heads.add(new ArrayList<>());

        for (String word: words) heads.get(word.charAt(0) - 'a').add(new Node(word));

        List<Node> newBucket = new ArrayList<>();
        for (int i = 0, len = s.length(); i < len; i++) {
            final char ch = s.charAt(i);
            final List<Node> oldBucket = heads.get(ch - 'a');
            heads.set(ch - 'a', newBucket);
            for (Node node : oldBucket) {
                if (node.move()) {
                    result++;
                } else {
                    heads.get(node.current() - 'a').add(node);
                }
            }
            oldBucket.clear();
            newBucket = oldBucket;
        }

        return result;
    }

    public static void main(String[] args) {
        Q792 q792 = new Q792();
        System.out.println(q792.numMatchingSubseq("abcde", new String[]{"a","bb","acd","ace","a"}));
    }
}
