package per.eicho.demo.leetcode.q1_100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>30. Substring with Concatenation of All Words 的题解代码 </p>
 * 
 * <pre>
 *  You are given a string s and an array of strings words of the same length. 
 *  Return all starting indices of substring(s) in s that is a concatenation of 
 *  each word in words exactly once, in any order, and without any intervening characters.
 *  You can return the answer in any order.
 * 
 * Example 1: 
 *    Input: s = "barfoothefoobarman", words = ["foo","bar"]
 *    Output: [0,9]
 *    Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 *    The output order does not matter, returning [9,0] is fine too.
 * 
 * Example 2:
 *    Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 *    Output: []
 * 
 * Example 3:
 *    Input: s = "barbarfoofoothefoobarman", words = ["foo","foo"]
 *    Output: [6]
 * 
 *  Constraints:
 *   1. 1 <= s.length <= 10^4
 *   2. s consists of lower-case English letters.
 *   3. 1 <= words.length <= 5000
 *   4. 1 <= words[i].length <= 30
 *   5. words[i] consists of lower-case English letters.
 * </pre>
 * @see <a href="https://leetcode.com/problems/intersection-of-two-arrays-ii/">350. Intersection of Two Arrays II</a>
 */
final public class Q30 {
    public List<Integer> findSubstring(String s, String[] words) {

        // prepare data
        final int length = s.length();
        final List<Integer> results = new ArrayList<>(length);
        
        // prepare word tree. 
        final WordTree wordTree = new WordTree(words);
        final List<WordTreeLeafNode> refLeafNodes = toLeafNodes(s, wordTree);

        // process
        for (int i = 0; i < length; i++) {
            //System.out.println((i+1) + "/" + length);
            if (isValidSubString(refLeafNodes, i, wordTree)) {
                results.add(i);
            }
        }

        return results;
    }

    private List<WordTreeLeafNode> toLeafNodes(final String s, final WordTree wordTree) {
        final List<WordTreeLeafNode> leafNodes = new ArrayList<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            leafNodes.add(i, wordTree.validateSubString(s, i));
        }

        return leafNodes;
    }

    private boolean isValidSubString(final List<WordTreeLeafNode> refLeafNodes, final int index, final WordTree wordTree) {
        if (index + wordTree.subStringLen > refLeafNodes.size()) {
            return false;
        }

        return isValidSubString(refLeafNodes, index, wordTree, wordTree.wordCount);
    }

    private Boolean isValidSubString(final List<WordTreeLeafNode> refLeafNodes, final int index, final WordTree wordTree, final int wordCount) {
        // check out of bounds.
        if (wordCount == 0) {
            return true;
        }

        final WordTreeLeafNode leafNode = refLeafNodes.get(index); 
        if (leafNode == null) {
            return false;
        }

        // check if runs out of leafNode.
        boolean runOutFlag = leafNode.decrease() == false;
        if (runOutFlag) {
            leafNode.increase(); // recover status of leaf node.
            return false; // run out of leaf node. 
        }

        // check recursively.
        boolean result = isValidSubString(refLeafNodes, index + wordTree.wordLen, wordTree, wordCount - 1);

        leafNode.increase(); // recover status of leaf node.
        return result;   
    }

    static final class WordTree {
        final String[] words;
        final int wordCount;
        final int wordLen;
        final int subStringLen;

        final WordTreeRootNode rootNode;

        WordTree(String[] words) {
            this.words = words;
            wordCount = words.length;
            wordLen = words[0].length();
            subStringLen = wordCount * wordLen;

            rootNode = new WordTreeRootNode();

            for (int i = 0; i < wordCount; i++) {
                final String word = words[i];

                WordTreeNode currentNode = rootNode;
                for (int j = 0; j < word.length(); j++) {
                    final boolean isLeaf = (j == word.length() - 1);

                    final char c = word.charAt(j);
                    final Character charInstance = boxing(c); // performance optimization [Boxing feature]

                    WordTreeNode sonNode = currentNode.get(charInstance);
                    if (sonNode == null) {
                        // Not Exist.
                        if (isLeaf) {
                            sonNode = new WordTreeLeafNode(charInstance);
                        } else {
                            sonNode = new WordTreeNormalNode(charInstance);
                        }

                        currentNode.put(charInstance, sonNode);
                    }

                    // assert sonNode != null;
                    currentNode = sonNode;

                    if (isLeaf) {
                        ((WordTreeLeafNode)currentNode).increase();
                    }
                }
            }
        }

        static final private List<Character> CHARACTERS;
        static {
            final List<Character> TEMP_CHARACTERS = new ArrayList<Character>(26);
            for (int i = 'a'; i <= 'z'; i++) {
                TEMP_CHARACTERS.add((char)i);
            }
            CHARACTERS = Collections.unmodifiableList(TEMP_CHARACTERS);
        }
        
        static private Character boxing(char c) {
            return CHARACTERS.get((c - 'a'));
        }

        WordTreeLeafNode validateSubString(final String s, final int l) {
            if (l + wordLen > s.length()) {
                return null; // out of range.
            }

            WordTreeNode currentNode = rootNode;

            for (int i = 0; i < wordLen; i++) {
                final int index = l + i;

                final char c = s.charAt(index);
                final Character charInstance = boxing(c); // performance optimization [Boxing feature]

                WordTreeNode sonNode = currentNode.get(charInstance); 
                if (sonNode == null) {
                    return null;
                }
                
                currentNode = sonNode;                
            }
            
            //assert currentNode instanceof WordTreeLeafNode;
            return (WordTreeLeafNode)currentNode;
        }
    }

    static abstract class WordTreeNode {
        final Map<Character, WordTreeNode> nodes;

        WordTreeNode() {
            this.nodes = new HashMap<Character, WordTreeNode>();
        }

        WordTreeNode get(Character c) {
            return nodes.get(c);
        }

        void put(Character key, WordTreeNode node) {
            nodes.put(key, node);
        }
    }

    static final class WordTreeRootNode extends WordTreeNode {

    }

    static class WordTreeNormalNode extends WordTreeNode {
        final Character character;

        WordTreeNormalNode(Character character) {
            super();
            this.character = character;
        }
    }

    static final class WordTreeLeafNode extends WordTreeNormalNode {
        int count;

        WordTreeLeafNode(Character character) {
            super(character);

            count = 0;
        }
        
        void increase() {
            count++;
        }

        boolean decrease() {
            count--;
            return count >= 0;
        }
    }

    public static void main(String[] args) {
        Q30 q30 = new Q30();

        final String s = "wordgoodgoodgoodbestword";
        final String[] words = new String[]{"word","good","best","word"};

        
        final List<Integer> result = q30.findSubstring(s, words);

        System.out.println("Count:" + result.size());
        System.out.println("[" + result.stream().map(i -> i.toString()).collect(Collectors.joining(",")) + "]");
    }
}