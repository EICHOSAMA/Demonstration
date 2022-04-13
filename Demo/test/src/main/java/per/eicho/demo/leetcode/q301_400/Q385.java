package per.eicho.demo.leetcode.q301_400;

import java.util.List;
import java.util.Stack;

/**
 * <p>385. Mini Parser 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/mini-parser/">
 *   385. Mini Parser</a>
 */
public final class Q385 {
    public static class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {};
    
        // Constructor initializes a single integer.
        public NestedInteger(int value) {};
    
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() { return false; }
    
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() { return -1; }
    
        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {}
    
        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {}
    
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() { return null; }
    }

    public NestedInteger deserialize(String s) {
        // 1. 1 <= s.length <= 5 * 10^4
        // 2. s consists of digits, square brackets "[]", negative sign '-', and commas ','.
        // 3. s is the serialization of valid NestedInteger.
        // 4. All the values in the input are in the range [-10^6, 10^6].
        final Stack<NestedInteger> stack = new Stack<>();
        final int n = s.length();
        int sign = 0;
        int val = 0;
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);

            if (ch == '[') {
                final NestedInteger newListNI = new NestedInteger();
                if (!stack.isEmpty()) stack.peek().add(newListNI);
                stack.push(newListNI);
            } else if (ch == ']') {
                final NestedInteger topNI = stack.pop();
                if (sign != 0) {
                    topNI.add(new NestedInteger(sign * val));
                    sign = 0;
                    val = 0;
                }
                if (stack.isEmpty()) return topNI;
            } else if (ch == ',') {
                final NestedInteger topNI = stack.peek();
                if (sign != 0) {
                    topNI.add(new NestedInteger(sign * val));
                    sign = 0;
                    val = 0;
                }
            } else if (ch == '-') {
                sign = -1;
            } else {
                if (sign == 0) sign = 1;
                // digit
                val = val * 10 + (ch - '0');
            }
        }
        return new NestedInteger(sign * val);
    }
}
