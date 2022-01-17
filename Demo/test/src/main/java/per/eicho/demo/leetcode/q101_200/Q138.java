package per.eicho.demo.leetcode.q101_200;

/**
 * <p>138. Copy List with Random Pointer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/copy-list-with-random-pointer/">138. Copy List with Random Pointer</a>
 */
public final class Q138 {
    
    private static final class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node result = null;
        Node cursor = head;

        while (cursor != null) {
            final Node newNode = new Node(cursor.val);
            
            newNode.next = cursor.next;
            cursor.next = newNode; 
            cursor = newNode.next;
        }

        cursor = head;
        while (cursor != null) {
            final Node oldNode = cursor;
            final Node newNode = cursor.next;

            if (oldNode.random != null) {
                newNode.random = oldNode.random.next;
            } 
            cursor = newNode.next;
        }

        cursor = head;
        result = head.next;
        while (cursor != null) {
            final Node oldNode = cursor;
            final Node newNode = cursor.next;

            cursor = newNode.next;
            oldNode.next = newNode.next;
            newNode.next = newNode.next != null ? newNode.next.next : null;
        }

        return result;
    }
}
