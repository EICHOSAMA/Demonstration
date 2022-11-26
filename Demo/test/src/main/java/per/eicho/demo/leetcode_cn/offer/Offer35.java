package per.eicho.demo.leetcode_cn.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>剑指 Offer 35. 复杂链表的复制 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/">
 *   剑指 Offer 35. 复杂链表的复制</a>
 */
public final class Offer35 {
    public Node copyRandomList(Node head) {
        // 1. -10000 <= Node.val <= 10000
        // 2. Node.random 为空（null）或指向链表中的节点。
        // 3. 节点数目不超过 1000 。    
        // head.random
        final Map<Node, Node> mapping = new HashMap<>();
        final Node copyHead = copySkeleton(head, mapping);
        recoverRandomLink(head, copyHead, mapping);
        return copyHead;
    }

    private Node copySkeleton(Node head, Map<Node, Node> mapping) {
        if (head == null) return null;
        final Node copyHead = new Node(head.val);
        mapping.put(head, copyHead);
        copyHead.next = copySkeleton(head.next, mapping);
        return copyHead;
    }

    private void recoverRandomLink(Node head, Node copyHead, Map<Node, Node> mapping) {
        Node cursor = head;
        Node copyCursor = copyHead;

        while (cursor != null) {
            if(cursor.random != null) {
                copyCursor.random = mapping.get(cursor.random);
            }

            cursor = cursor.next;
            copyCursor = copyCursor.next;
        }
    }

    private static final class Node {
        int val;
        Node next;
        Node random;
    
        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
