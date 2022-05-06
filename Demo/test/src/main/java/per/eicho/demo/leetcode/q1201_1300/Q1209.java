package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1209. Remove All Adjacent Duplicates in String II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/">
 *   1209. Remove All Adjacent Duplicates in String II</a>
 */
public final class Q1209 {

    private static final class LinkedListNode {

        final char ch;
        int count;

        LinkedListNode prev;
        LinkedListNode next;

        LinkedListNode(char ch) {
            this.ch = ch;
            count = 1;

            prev = null;
            next = null;
        }

        void insert(LinkedListNode node) {
            node.next = this.next;
            node.prev = this;

            node.next.prev = node;
            this.next = node;
        }

        LinkedListNode removeAndTryMerge() {
            final LinkedListNode prev = this.prev;
            final LinkedListNode next = this.next;

            prev.next = next;
            next.prev = prev;

            this.next = null;
            this.prev = null;

            if (prev.ch == next.ch) {
                prev.count += next.count;
                next.removeAndTryMerge();
            }

            return prev;
        }
    }

    public String removeDuplicates(String s, int k) {
        // 1. 1 <= s.length <= 10^5
        // 2. 2 <= k <= 10^4
        // 3. s only contains lower case English letters.

        final LinkedListNode vituralHead = new LinkedListNode('-');
        final LinkedListNode vituralTail = new LinkedListNode(';');
        vituralHead.next = vituralTail;
        vituralTail.prev = vituralHead;

        final int n = s.length();
        for (int i = 0; i < n; i++) {
            final char ch = s.charAt(i);

            if (vituralTail.prev.ch == ch) {
                vituralTail.prev.count++;
            } else {
                vituralTail.prev.insert(new LinkedListNode(ch));
            }
        }

        LinkedListNode cursor = vituralHead;
        while (cursor != vituralTail) {
            cursor = cursor.next; // move;
            if (cursor.count < k) continue;
            cursor.count %= k;
            if (cursor.count != 0) continue;
            cursor = cursor.removeAndTryMerge();
            if (cursor.prev != null) cursor = cursor.prev;
        }

        final StringBuilder sb = new StringBuilder();
        cursor = vituralHead.next;
        while (cursor != vituralTail) {
            for (int i = 0; i < cursor.count; i++) sb.append(cursor.ch);
            cursor = cursor.next;
        }

        return sb.toString();
    }
}
