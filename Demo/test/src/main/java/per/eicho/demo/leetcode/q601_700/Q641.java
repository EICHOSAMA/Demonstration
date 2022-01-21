package per.eicho.demo.leetcode.q601_700;

/**
 * <p>641. Design Circular Deque 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-circular-deque/">641. Design Circular Deque</a>
 */
@SuppressWarnings("unused")
public final class Q641 {
    private  static final class MyCircularDeque {

        final int capacity;
        final ListNode vituralHead;
        final ListNode vituralTail;
        int count;

        private class ListNode {
            final int val;

            ListNode previous;
            ListNode next;

            ListNode(int val) {
                this.val = val;
            }
        }

        /**
         * Initializes the deque with a maximum size of k.
         */
        public MyCircularDeque(int k) {
            // 1 <= k <= 1000
            capacity = k;

            vituralHead = new ListNode(-1);
            vituralTail = new ListNode(1001);
            vituralHead.next = vituralTail;
            vituralTail.previous = vituralHead;
            count = 0;
        }
        
        /**
         * Adds an item at the front of Deque. 
         * Returns true if the operation is successful, or false otherwise.
         */
        public boolean insertFront(int value) {
            if (count == capacity) return false;
            final ListNode node = new ListNode(value);
            node.next = vituralHead.next;
            node.previous = vituralHead;

            vituralHead.next.previous = node;
            vituralHead.next = node;
            count++;

            return true;
        }
        
        /**
         * Adds an item at the rear of Deque. 
         * Returns true if the operation is successful, or false otherwise.
         */
        public boolean insertLast(int value) {
            if (count == capacity) return false;
            final ListNode node = new ListNode(value);
            node.previous = vituralTail.previous;
            node.next = vituralTail;

            vituralTail.previous.next = node;
            vituralTail.previous = node;
            count++;

            return true;
        }
        
        /**
         * Deletes an item from the front of Deque. 
         * Returns true if the operation is successful, or false otherwise.
         */
        public boolean deleteFront() {
            if (count == 0) return false;
            
            vituralHead.next = vituralHead.next.next;
            vituralHead.next.previous = vituralHead;
            count--;

            return true;
        }
        
        /**
         * Deletes an item from the rear of Deque. 
         * Returns true if the operation is successful, or false otherwise.
         */
        public boolean deleteLast() {
            if (count == 0) return false;

            vituralTail.previous = vituralTail.previous.previous;
            vituralTail.previous.next = vituralTail;
            count--;

            return true;
        }
        
        /**
         * Returns the front item from the Deque. 
         * Returns -1 if the deque is empty.
         */
        public int getFront() {
            return count == 0 ? -1 : vituralHead.next.val;
        }
        
        /**
         * Returns the last item from Deque. 
         * Returns -1 if the deque is empty.
         */
        public int getRear() {
            return count == 0 ? -1 : vituralTail.previous.val;
        }
        
        /**
         * Returns true if the deque is empty, or false otherwise.
         */
        public boolean isEmpty() {
            return count == 0;
        }
        
        /**
         * Returns true if the deque is full, or false otherwise. 
         */
        public boolean isFull() {
            return count == capacity;
        }
    }
}
