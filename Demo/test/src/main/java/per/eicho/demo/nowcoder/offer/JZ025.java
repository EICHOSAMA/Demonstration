package per.eicho.demo.nowcoder.offer;

import per.eicho.demo.nowcoder.datastructure.ListNode;

public final class JZ025 {
    public ListNode merge(ListNode list1,ListNode list2) {
        // 节点值: [-1000, 1000], n: [0, 1000]
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        final ListNode virtualHead = new ListNode(Integer.MIN_VALUE);
        ListNode p = virtualHead;

        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p = p.next;

                p1 = p1.next;
                p.next = null;
            } else {
                p.next = p2;
                p = p.next;
                
                p2 = p2.next;
                p.next = null;
            }
        }

        if (p1 == null) {
            p.next = p2;
        } else {
            p.next = p1;
        }
        return virtualHead.next;
    }
}
