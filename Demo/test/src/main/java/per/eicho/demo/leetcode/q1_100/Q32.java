package per.eicho.demo.leetcode.q1_100;

/**
 * 32. Longest Valid Parentheses 的题解代码
 * 
 * 
 * <pre>
 *  Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * 
 *  Constraints:
 *    1. 0 <= s.length <= 3 * 104
 *    2. s[i] is '(', or ')'.
 * </pre>
 * @see <a href="https://leetcode.com/problems/longest-valid-parentheses/">32. Longest Valid Parentheses</a>
 */
final public class Q32 {
    static abstract class ProcessNode {
        ProcessNode leftNode;
        ProcessNode rightNode;
    }

    static final class StartNode extends ProcessNode {

    }
    
    static final class EndNode extends ProcessNode {

    }

    static final class ProcessNodeFactory {

        final static char LEFT_PARENTHESIS = '(';
        final static char RIGHT_PARENTHESIS = ')';  

        InvalidNode newInstance(final char ch, final int index) {
            if (ch == LEFT_PARENTHESIS) {
                return new LeftNode(index);
            } else if (ch == RIGHT_PARENTHESIS) {
                return new RightNode(index);
            }
            throw new IllegalArgumentException("ARG ch mush be Exactly one of '(' or ')'");
        }

        StartNode prepareNodes(final String s) {
            final char[] chars = s.toCharArray();

            final StartNode startNode = new StartNode();
            
            ProcessNode currentNode = startNode;
            int index = 0;
            for (char ch :chars) {
                final InvalidNode node = newInstance(ch, index);
                
                // create link
                currentNode.rightNode = node;
                node.leftNode = currentNode;

                // move
                currentNode = node;
                index++;
            }

            final EndNode endNode = new EndNode();
            currentNode.rightNode = endNode;
            endNode.rightNode = currentNode;

            return startNode;
        }
    }

    static abstract class InvalidNode extends ProcessNode {
        final int index;

        InvalidNode(int index) {
            this.index = index;
        }
    }

    static final class LeftNode extends InvalidNode {
        LeftNode(int index) {
            super(index);
        }
    }

    static final class RightNode extends InvalidNode {
        RightNode(int index) {
            super(index);
        }
    }

    static final class ValidNode extends ProcessNode {
        final int left;
        final int right;

        ValidNode(int left, int right) {
            //assert left < right;
            this.left = left;
            this.right = right;
        }

        int length() {
            return right - left + 1;
        }
    }
    

    
    public int longestValidParentheses(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }

        StartNode startNode = new ProcessNodeFactory().prepareNodes(s);
        return process(startNode);
    }

    private int process(final StartNode startNode) {
        int result = 0;

        ProcessNode currentNode = startNode.rightNode;

        while (!(currentNode instanceof EndNode)) {

            if (currentNode instanceof ValidNode) {
                // Merge ValidNode.Case1 
                if (currentNode.leftNode instanceof ValidNode) {
                    final ValidNode leftNode = (ValidNode)currentNode.leftNode;
                    final ValidNode newValidNode = new ValidNode(leftNode.left, ((ValidNode)currentNode).right);

                    newValidNode.leftNode = leftNode.leftNode;
                    newValidNode.rightNode = currentNode.rightNode;

                    newValidNode.leftNode.rightNode = newValidNode;
                    newValidNode.rightNode.leftNode = newValidNode;
                    
                    currentNode = newValidNode;
                    continue;
                }

                // Merge ValidNode.Case2
                if (currentNode.leftNode instanceof LeftNode && currentNode.rightNode instanceof RightNode) {
                    final LeftNode leftNode = (LeftNode)currentNode.leftNode;
                    final RightNode rightNode = (RightNode)currentNode.rightNode;
                    final ValidNode newValidNode = new ValidNode(leftNode.index, rightNode.index);

                    newValidNode.leftNode = leftNode.leftNode;
                    newValidNode.rightNode = rightNode.rightNode;

                    newValidNode.leftNode.rightNode = newValidNode;
                    newValidNode.rightNode.leftNode = newValidNode;
                    
                    currentNode = newValidNode;
                    continue;                    
                }

                // update result if bigger than current result.
                final ValidNode validNode = (ValidNode)currentNode;
                if (validNode.length() > result) {
                    result = validNode.length();
                }
            } else if (currentNode instanceof RightNode) {
                // Merge ValidNode.Case3
                if (currentNode.leftNode instanceof LeftNode) {
                    final LeftNode leftNode = (LeftNode)currentNode.leftNode;
                    final RightNode rightNode = (RightNode)currentNode;
                    final ValidNode newValidNode = new ValidNode(leftNode.index, rightNode.index);
               
                    newValidNode.leftNode = leftNode;
                    newValidNode.rightNode = rightNode;
                    
                    newValidNode.leftNode.rightNode = newValidNode;
                    newValidNode.rightNode.leftNode = newValidNode;
                    
                    currentNode = newValidNode;
                    continue;
                }
            }

            // move to next
            currentNode = currentNode.rightNode;
        }

        return result;
    }

    public static void main(String[] args) {
        Q32 q32 = new Q32();

        System.out.print(q32.longestValidParentheses("(((()))()()()()((())))))))"));
    }
}
