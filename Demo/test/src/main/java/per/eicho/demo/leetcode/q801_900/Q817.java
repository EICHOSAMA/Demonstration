package per.eicho.demo.leetcode.q801_900;

import java.util.HashSet;
import java.util.Set;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>817. Linked List Components 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-components/">817. Linked List Components</a>
 */
public final class Q817 {
        public int numComponents(ListNode head, int[] nums) {
            // 1. The number of nodes in the linked list is n.
            // 2. 1 <= n <= 10^4
            // 3. 0 <= Node.val < n, All the values Node.val are unique.
            // 4. 1 <= nums.length <= n
            // 5. 0 <= nums[i] < n, All the values of nums are unique.
            final Set<Integer> numSet = new HashSet<>();
            for (int num: nums) {
                numSet.add(num);
            }

            ListNode cursor = head;
            int result = 0;
    
            while (cursor != null) {
                if (numSet.contains(cursor.val)) {
                    if (cursor.next == null || !numSet.contains(cursor.next.val)) result++;
                }

                cursor = cursor.next;
            }
    
            return result;
        }
}
