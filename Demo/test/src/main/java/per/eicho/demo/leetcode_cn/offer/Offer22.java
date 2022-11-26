package per.eicho.demo.leetcode_cn.offer;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>剑指 Offer 22. 链表中倒数第k个节点 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/">
 *   剑指 Offer 22. 链表中倒数第k个节点</a>
 */
public final class Offer22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode l = head, r = head; // window [l, r)
        int windowSize = 0;
        while (r != null) {
            while (r != null && windowSize < k) {
                r = r.next;
                windowSize++;
            }

            if (r == null) {
                if (windowSize == k) return l;
                return null; // k > n
            }

            l = l.next;
            windowSize--;
        }

        return l;
    }
}
