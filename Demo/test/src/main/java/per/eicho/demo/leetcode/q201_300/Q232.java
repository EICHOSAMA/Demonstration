package per.eicho.demo.leetcode.q201_300;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>232. Implement Queue using Stacks 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-queue-using-stacks/">232. Implement Queue using Stacks</a>
 */
@SuppressWarnings("unused")
public final class Q232 {
    private final static class MyQueue {
        private Deque<Integer> deque;
    
        /** Initialize your data structure here. */
        public MyQueue() {
            deque = new LinkedList<>();
        }
    
        /** Push element x to the back of queue. */
        public void push(int x) {
            deque.addLast(x);
        }
    
        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            // assert deque.size() != 0: "You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).";
            return deque.pollFirst();
        }
    
        /** Get the front element. */
        public int peek() {
            // assert deque.size() != 0: "You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).";
            return deque.peekFirst();
        }
    
        /** Returns whether the queue is empty. */
        public boolean empty() {
            return deque.size() == 0;
        }
    }
}
