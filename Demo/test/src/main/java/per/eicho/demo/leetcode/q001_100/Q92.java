package per.eicho.demo.leetcode.q001_100;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>92. Reverse Linked List II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-linked-list-ii/">92. Reverse Linked List II</a>
 */
final public class Q92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        final ListNode root = new ListNode(0);
        root.next = head;

        ListNode p = root;
        int position = 0;
        ListNode ed = p;
        
        
        final Stack<ListNode> stack = new Stack<>();
        while (p != null) {
            p = p.next;
            position++;

            if (position < left) {
                ed = p;
            } else  if (left <= position && position <= right) {
                stack.add(p);
            } else {
                assert right < position;
                
                while (stack.size() != 0) {
                    ed.next = stack.pop();
                    ed = ed.next;
                }
                ed.next = p;

                break;
            }
        }

        return root.next;
    }
}
