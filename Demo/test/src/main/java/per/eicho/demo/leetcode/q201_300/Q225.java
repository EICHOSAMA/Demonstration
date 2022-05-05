package per.eicho.demo.leetcode.q201_300;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>225. Implement Stack using Queues 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-stack-using-queues/">225. Implement Stack using Queues</a>
 */
@SuppressWarnings("unused")
public final class Q225 {
    private static class MyStack {
        private Deque<Integer> deque;
    
        /** Initialize your data structure here. */
        public MyStack() {
            deque = new LinkedList<>();
        }
    
        /** Push element x onto stack. */
        public void push(int x) {
            deque.addLast(x);
        }
    
        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            // assert deque.size() != 0: "You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).";
            return deque.pollLast();
        }
    
        /** Get the top element. */
        public int top() {
            // assert deque.size() != 0: "You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).";
            return deque.peekLast();
        }
    
        /** Returns whether the stack is empty. */
        public boolean empty() {
            return deque.size() == 0;
        }
    }
    
}
