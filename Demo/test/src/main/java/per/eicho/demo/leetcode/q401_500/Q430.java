package per.eicho.demo.leetcode.q401_500;

/**
 * <p>430. Flatten a Multilevel Doubly Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/">430. Flatten a Multilevel Doubly Linked List</a>
 */
public final class Q430 {

    private static class Node {
        @SuppressWarnings("unused")
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };

    public Node flatten(Node head) {
        if (head == null) return null;
        return flattenNode(head).prev;
    }

    private Node flattenNode(Node node) {
        final Node result = new Node();
        result.prev = node;

        Node cursor = node;
        Node tail = node;
        while (cursor != null) {
            if (cursor.child == null) {
                tail = cursor;
                cursor = cursor.next;
                continue;
            }

            final Node flattedChildList = flattenNode(cursor.child);
            cursor.child = null;

            final Node childListHead = flattedChildList.prev;
            final Node childListTail = flattedChildList.next;

            if (cursor.next != null) {
                childListTail.next = cursor.next;
                childListTail.next.prev = childListTail;    
            }

            cursor.next = childListHead;
            childListHead.prev = cursor;

            tail = childListTail;
            cursor = tail.next;
        }

        result.next = tail;

        result.prev.prev = null;
        result.next.next = null;

        return result;
    }
}
