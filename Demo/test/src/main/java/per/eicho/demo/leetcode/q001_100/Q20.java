package per.eicho.demo.leetcode.q001_100;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>20. Valid Parentheses 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-parentheses/">20. Valid Parentheses</a>
 */
public final class Q20 {
    private static Character LEFT_PARENTHESES = '('; // 40
    //    private static Character RIGHT_PARENTHESES = ')'; // 41
    
        private static Character LEFT_SQUARE_BRACKET = '['; // 91
    //    private static Character RIGHT_SQUARE_BRACKET = ']'; // 93
    
        private static Character LEFT_CURLY_BRACKET = '{'; // 123
    //    private static Character RIGHT_CURLY_BRACKET = '}'; // 125
    
    
        public boolean isValid(String s) {
            final int len = s.length();
            if (len == 0)
                return true;
    
            List<Character> stack = new LinkedList<>();
            int i = 0, size;
            char ch;
            while (i < len) {
                ch = s.charAt(i++);
    
                /**
                 * judge.
                 */
                if (ch == '(')
                    stack.add(LEFT_PARENTHESES);
                else if (ch == '[')
                    stack.add(LEFT_SQUARE_BRACKET);
                else if (ch == '{')
                    stack.add(LEFT_CURLY_BRACKET);
                else if (ch == ')') {
                    size = stack.size();
                    if (size == 0 ||
                        stack.remove(size - 1) != LEFT_PARENTHESES)
                        return false; //invalid
                } else if (ch == ']') {
                    size = stack.size();
                    if (size == 0 ||
                        stack.remove(size - 1) != LEFT_SQUARE_BRACKET)
                        return false; //invalid
                } else if (ch == '}') {
                    size = stack.size();
                    if (size == 0 ||
                        stack.remove(size - 1) != LEFT_CURLY_BRACKET)
                        return false; //invalid
                }
            }
    
            return stack.size() == 0; // not empty, means not closed.
        }
}
