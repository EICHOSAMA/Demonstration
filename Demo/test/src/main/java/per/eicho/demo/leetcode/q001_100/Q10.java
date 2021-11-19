package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 10. Regular Expression Matching 的题解代码
 * 
 * 
 * <pre>
 *  Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 
 *    '.' Matches any single character.​​​​
 *    '*' Matches zero or more of the preceding element.
 *  The matching should cover the entire input string (not partial).
 * </pre>
 * @see <a href="https://leetcode.com/problems/regular-expression-matching/">10. Regular Expression Matching</a>
 */
final public class Q10 {

    public static void main(String[] args) {
        Q10 q10 = new Q10();

        System.out.println(q10.isMatch("ab", ".*"));
        System.out.println(q10.isMatch("a", "ab*"));
        System.out.println(q10.isMatch("abbbbbbbbbbbbbbc", "ab*"));
        System.out.println(q10.isMatch("abbbbbbbbbbbbbbc", "ab*c"));
    }

    static private class Pattern {
        static final char CHAR_Quantifier = '*';
        static final char CHAR_SingleChar = '.';

        private final List<PatternNode> nodes;

        Pattern(List<PatternNode> nodes) {
            this.nodes = Collections.unmodifiableList(nodes);
        }

        static Pattern compile(String pattern) {
            final List<PatternNode> nodes = new ArrayList<>(pattern.length());

            for (int i = 0; i < pattern.length(); i++) {
                final char c = pattern.charAt(i);

                if (c == CHAR_SingleChar) {
                    nodes.add(new AnyCharNode());
                } else if (c == CHAR_Quantifier) {
                    final int indexOfLastNode = nodes.size() - 1;
                    PatternNode previousNode = nodes.get(indexOfLastNode); // get last node.
                    nodes.remove(indexOfLastNode);
                    nodes.add(new CharGreedyQuantifierNode(previousNode)); // warp
                } else {
                    nodes.add(new CharacterNode(c));
                }
            }

            return new Pattern(nodes);
        }

        public boolean isMatch(String input) {
            return doMatch(input, 0, 0);
        }

        /**
         * <p>Matching Process</p>
         * 
         * <pre>
         * To find out if remaing part of string ([si, input.len)) matches the remaing part of nodes [ni, nodes.len).
         * </pre>
         * 
         * @param input target string.
         * @param si the index of the processing character.
         * @param ni the index of the processing node.
         * @return true or false to show whether the remaing part of string matches the remaing part of nodes.
         */
        private boolean doMatch(final String input, int si, int ni) {
            if (si == input.length() && ni == this.nodes.size()) {
                return true;
            }

            // run out of nodes.
            if (ni == this.nodes.size() && si < input.length()) {
                return false;
            }

            final PatternNode currentNode = this.nodes.get(ni);
            final Matches currentMatches = currentNode.matches(input, si);

            if (currentMatches.result.size() == 0) {
                // match failed.
                return false;
            }

            while (currentMatches.result.size() != 0) {
                final Match match = currentMatches.result.pop();
                final int matchedLength = match.matchedLength;

                final boolean result = doMatch(input, si + matchedLength, ni + 1);
                if (result) {
                    return true;
                }
            }

            return false;
        }
    }

    static private abstract class PatternNode {

        /**
         * <p>Find out all possible matches</p>
         * 
         * <pre>
         * Find out all possible matches start from the given index of the given string.
         * </pre>
         * 
         * @param target target string, non-null but can be empty.
         * @param index range [0, target.length], when equals to target.length means all characters has been matched.  
         *  The only possible matching result is 0.
         * @return a collection of Match. Non-null
         */
        public Matches matches(final String target, final int index) {
            Objects.requireNonNull(target);

            return doMatches(target, index);
        }

        abstract Matches doMatches(final String target, final int index);
    }

    static private class Matches {
        Stack<Match> result = new Stack<>();
    }

    static private final class Match {
        final int matchedLength;

        Match(int matchedLength) {
            this.matchedLength = matchedLength;
        }
    }

    static private class CharacterNode extends PatternNode {
        final private char c;

        CharacterNode(char c) {
            this.c = c;
        }

        @Override
        Matches doMatches(final String target, final int index) {
            final Matches matches = new Matches();
            
            if (target.length() == index) {
                return matches; // empty
            }

            if (target.charAt(index) == this.c) {
                matches.result.add(new Match(1));
            }

            return matches;
        }
    }

    static private class AnyCharNode extends PatternNode {

        @Override
        Matches doMatches(final String target, final int index) {
            final Matches matches = new Matches();
            
            if (target.length() == index) {
                return matches; // empty
            }
            
            matches.result.add(new Match(1));
            return matches;
        }
        
    }

    static private class CharGreedyQuantifierNode extends PatternNode {
        final private PatternNode node;
        
        CharGreedyQuantifierNode(PatternNode node) {
            Objects.requireNonNull(node);
            this.node = node;
        }

        @Override
        Matches doMatches(final String target, final int index) {
            final Matches matches = new Matches();
            matches.result.add(new Match(0));

            if (target.length() == index) {
                return matches;
            }

            for (int i = index; i < target.length(); i++) {
                if (node.doMatches(target, i).result.size() == 0) {
                    break;
                }

                matches.result.add(new Match(i - index + 1));
            }
            
            return matches;
        }
    }

    /**
     * <p>Matching</p>
     * 
     * <pre>
     * to find out if the giving string s matchs the given pattern p.
     * The matching will cover the entire input string (not partial).
     * 
     * Constraints:
     *   0 <= s.length <= 20
     *   0 <= p.length <= 30
     *   s contains only lowercase English letters.
     *   p contains only lowercase English letters, '.', and '*'.
     *     It is guaranteed for each appearance of the character '*', 
     *     there will be a previous valid character to match.
     * 
     * Syntax:
     *   1. English Letter: match a specific english letter.
     *   2. '.' : Matches any single character.​​​​
     *   3. '*' : [Greedy Quantifier] 
     *       matches any string that contains zero or more occurrences of previous character.
     * </pre>
     * 
     * @param s the string to validate.
     * @param p the pattern string. Regular expression only support '.' and '*'
     * @return whether or not the string matchs the pattern.
     */
    public boolean isMatch(String s, String p) {
        final Pattern pattern = Pattern.compile(p);
        return pattern.isMatch(s);
    }
}
