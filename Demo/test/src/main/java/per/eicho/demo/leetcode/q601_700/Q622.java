package per.eicho.demo.leetcode.q601_700;

/**
 * <p>622. Design Circular Queue 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-circular-queue/">622. Design Circular Queue</a>
 */
@SuppressWarnings("unuse")
public final class Q622 {
    private static final class MyCircularQueue {

        private static final int NODE_VAL_UNUSED = -1;

        final int[] queue;
        final int capacity;
        int count;

        int head;
        int tail;

        /** Initializes the object with the size of the queue to be k. */
        public MyCircularQueue(int k) {
            // 1 <= k <= 1000
            capacity = k;
            queue = new int[k];
            count = 0;

            head = 0;
            tail = 0;
            
            for (int i = 0; i < k; i++) {
                queue[i] = NODE_VAL_UNUSED;
            }
        }
        
        /** Inserts an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            // 0 <= value <= 1000
            if (isFull()) return false;

            count++;
            queue[tail] = value;
            tail = next(tail);

            return true;
        }
        
        /** Deletes an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if (isEmpty()) return false;
            
            count--;
            queue[head] = NODE_VAL_UNUSED;
            head = next(head);

            return true;
        }
        
        /** Gets the front item from the queue. If the queue is empty, return -1. */
        public int Front() {
            return queue[head];
        }
        
        /** Gets the last item from the queue. If the queue is empty, return -1. */
        public int Rear() {
            return queue[prev(tail)];
        }
        
        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return count == 0;
        }
        
        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return count == capacity;
        }
    
        private int next(int index) {
            return (index + 1) % capacity;
        }

        private int prev(int index) {
            return (index - 1 + capacity) % capacity;
        }
    }
 
    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1));  // return True
        System.out.println(myCircularQueue.enQueue(2));  // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.enQueue(4)); // return False
        System.out.println(myCircularQueue.Rear());     // return 3
        System.out.println(myCircularQueue.isFull());   // return True
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.enQueue(4)); // return True
        System.out.println(myCircularQueue.Rear());     // return 4
        System.out.println(myCircularQueue.Front());    // retuyrn 2
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.Front());    // retuyrn 3
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.Front());    // retuyrn 4
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.Front());    // retuyrn -1
    }
}
