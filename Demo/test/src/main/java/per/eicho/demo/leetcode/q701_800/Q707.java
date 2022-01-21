package per.eicho.demo.leetcode.q701_800;

/**
 * <p>707. Design Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-linked-list/">707. Design Linked List</a>
 */
@SuppressWarnings("unused")
public final class Q707 {
    private static final class MyLinkedList {

        private class Node {
            final int val;
            Node next;
            Node previous;

            Node(int val) {
                this.val = val;
            }

        }

        final Node vituralHead;
        Node tail;
        int count;

        /**
         * Initializes the MyLinkedList object.
         */
        public MyLinkedList() {
            vituralHead = new Node(-1);
            count = 0;
            tail = null;
        }
        
        /**
         * Get the value of the index'th node in the linked list. 
         * If the index is invalid, return -1.
         */
        public int get(int index) {
            if (!isValidIndex(index)) return -1;

            Node cursor = vituralHead.next;
            while (index != 0) {
                cursor = cursor.next;
                index--;
            }

            return cursor.val; 
        }
        
        /**
         * Add a node of value val before the first element of the linked list. 
         * After the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            final Node newNode = new Node(val);

            if (count == 0) {
                vituralHead.next = newNode;
                newNode.previous = vituralHead;
                tail = newNode;
            } else {
                newNode.previous = vituralHead;
                newNode.next = vituralHead.next;
                
                newNode.previous.next = newNode;
                newNode.next.previous = newNode;
            }

            count++;
        }
        
        /**
         * Append a node of value val as the last element of the linked list.
         */
        public void addAtTail(int val) {
            final Node newNode = new Node(val);

            if (count == 0) {
                vituralHead.next = newNode;
                newNode.previous = vituralHead;

                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.previous = tail;

                tail = newNode;
            }

            count++;
        }
        
        /**
         * Add a node of value val before the indexth node in the linked list. 
         * If index equals the length of the linked list, 
         * the node will be appended to the end of the linked list. 
         * If index is greater than the length, <b>the node will not be inserted.</b>
         */
        public void addAtIndex(int index, int val) {
            if (index < 0 || index > count) return;
            if (index == count) {
                addAtTail(val);
                return;
            }

            Node cursor = vituralHead.next;
            int step = index;
            while (step != 0) {
                cursor = cursor.next;
                step--;
            }

            final Node newNode = new Node(val);

            newNode.next = cursor;
            newNode.previous = cursor.previous;
            
            newNode.previous.next = newNode;
            newNode.next.previous = newNode;
            count++;
        }
        
        /**
         * Delete the indexth node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            if (!isValidIndex(index)) return;

            Node cursor = vituralHead.next;
            int step = index;
            while (step != 0) {
                cursor = cursor.next;
                step--;
            }

            cursor.previous.next = cursor.next;
            if (cursor.next != null) {
                cursor.next.previous = cursor.previous;
            } else {
                tail = cursor.previous;
            }

            cursor.previous = null;
            cursor.next = null;

            count--;
        }

        private boolean isValidIndex(int index) {
            return 0 <= index && index < count; // [0, count)
        }
    }
}
