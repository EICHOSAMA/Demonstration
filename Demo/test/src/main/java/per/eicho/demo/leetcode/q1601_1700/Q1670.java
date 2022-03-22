package per.eicho.demo.leetcode.q1601_1700;

/**
 * <p>1670. Design Front Middle Back Queue 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-front-middle-back-queue/">
 *   1670. Design Front Middle Back Queue</a>
 */
@SuppressWarnings("unuse")
public final class Q1670 {
    private static final class FrontMiddleBackQueue {

        final ListNode vituralHead;
        final ListNode vituralTail;
        int count;
        ListNode midNodeL;
        ListNode midNodeR;

        private class ListNode {
            final int val;

            ListNode previous;
            ListNode next;

            ListNode(int val) {
                this.val = val;
            }
        }

        /** Initializes the queue. */
        public FrontMiddleBackQueue() {
            // 1 <= val <= 10^9
            vituralHead = new ListNode(-1);
            vituralTail = new ListNode(1_000_000_001);
            vituralHead.next = vituralTail;
            vituralTail.previous = vituralHead;
            count = 0;

            midNodeL = vituralHead;
            midNodeR = vituralTail;
        }
        
        /** Adds val to the front of the queue. */
        public void pushFront(int val) {
            final ListNode node = new ListNode(val);
            insertBeforeNode(node, vituralHead.next);

            // maintain mid node pointer.
            if (count % 2 == 1) {
                // odd
                midNodeR = midNodeR.previous;
                midNodeL = midNodeR;
            } else {
                // even
                midNodeL = midNodeR.previous; 
            }
        }
        
        /** Adds val to the middle of the queue. */
        public void pushMiddle(int val) {
            // Notice that when there are two middle position choices, 
            // the operation is performed on the frontmost middle position choice.
            // i.e. Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
            final ListNode node = new ListNode(val);
            insertBeforeNode(node, midNodeR);

            // maintain mid node pointer.
            if (count % 2 == 1) {
                // odd
                midNodeL = node;
                midNodeR = node;
            } else {
                // even
                midNodeL = midNodeR.previous;
            }            
        }
        
        /** Adds val to the back of the queue. */
        public void pushBack(int val) {
            final ListNode node = new ListNode(val);
            insertBeforeNode(node, vituralTail);

            // maintain mid node pointer.
            if (count % 2 == 1) {
                // odd
                midNodeL = midNodeL.next;
                midNodeR = midNodeL;
            } else {
                // even
                midNodeR = midNodeL.next; 
            }
        }
        
        /** Removes the front element of the queue and returns it. If the queue is empty, return -1. */
        public int popFront() {
            if (count == 0) return -1;
            
            final ListNode oldFrontNode = vituralHead.next;
            removeNode(oldFrontNode);

            // maintain mid node pointer.
            if (count % 2 == 1) {
                // even ⇒ odd
                midNodeL = midNodeR;
            } else {
                // odd ⇒ even
                // assert midNodeR == midNodeL
                midNodeR = midNodeL.next;
                midNodeL = midNodeR.previous;
            }

            return oldFrontNode.val;
        }
        
        /** Removes the middle element of the queue and returns it. If the queue is empty, return -1. */
        public int popMiddle() {
            if (count == 0) return -1;

            final ListNode oldMiddleNode = midNodeL;
            removeNode(midNodeL);

            if (count % 2 == 1) {
                // odd
                midNodeL = midNodeR;
            } else {
                // even
                midNodeL = midNodeL.previous;
                midNodeR = midNodeR.next;
            }

            return oldMiddleNode.val;
        }
        
        /** Removes the back element of the queue and returns it. If the queue is empty, return -1. */
        public int popBack() {
            if (count == 0) return -1;

            final ListNode oldBackNode = vituralTail.previous;
            removeNode(oldBackNode);

            // maintain mid node pointer.
            if (count % 2 == 1) {
                // even ⇒ odd
                midNodeR = midNodeL;
            } else {
                // odd ⇒ even
                // assert midNodeR == midNodeL
                midNodeL = midNodeR.previous;
                midNodeR = midNodeL.next;
            }

            return oldBackNode.val;
        }

        private void insertBeforeNode(ListNode insertedNode, ListNode insertPosition) {
            // 1. maintain count info.
            count++;

            // 2. insert node.
            insertedNode.next = insertPosition;
            insertedNode.previous = insertPosition.previous;

            insertedNode.previous.next = insertedNode;
            insertedNode.next.previous = insertedNode;
        }

        /** 
         * remove specific node from inner queue.
         * this method will maintain count field. (count--)
         */
        private void removeNode(ListNode node) {
            // assert count > 0;
            // 1. maintain count info.
            count--;

            // 2. remove node form the queue.
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }

        private void printQueue() {
            System.out.print("H→");

            ListNode cursor = vituralHead.next;
            while (cursor != vituralTail) {
                System.out.print(cursor.val + "→");
                cursor = cursor.next;
            }

            System.out.print("T, ");
            System.out.println("{" + midNodeL.val + "," + midNodeR.val + "}");
        }
    }
    
    public static void main(String[] args) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        
        q.pushMiddle(874835);
        q.printQueue();

        System.out.println(q.popBack()); 
        q.printQueue();

        System.out.println(q.popMiddle()); 
        q.printQueue();

        System.out.println(q.popMiddle()); 
        q.printQueue();

        q.pushBack(319750);
        q.printQueue();

        q.pushFront(782168);
        q.printQueue();

        System.out.println(q.popFront()); 
        q.printQueue();

        q.pushMiddle(16473);
        q.printQueue();

        q.pushMiddle(495349);
        q.printQueue();

        System.out.println(q.popMiddle()); 
        q.printQueue();

        System.out.println(q.popMiddle()); 
        q.printQueue();

        q.pushMiddle(596131);
        q.printQueue();

        System.out.println(q.popMiddle()); 
        q.printQueue();

        q.pushMiddle(583563);
        q.printQueue();
    }
}
