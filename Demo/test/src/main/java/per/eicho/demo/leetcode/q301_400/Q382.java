package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>382. Linked List Random Node 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-random-node/">382. Linked List Random Node</a>
 */
public final class Q382 {

    final static int NUM_PER_INDEX = 100;

    final int count;
    final ListNode head;
    final List<ListNode> indexes;
    final Random random;

    public Q382(ListNode head) {
        this.head = head;
        indexes = new ArrayList<>();
        count = getNodeCountAndCreateIndex(head);

        random = new Random();
    }

    private int getNodeCountAndCreateIndex(ListNode head) {
        ListNode cursor = head;
        int cnt = 0;
        while (cursor != null) {
            if (cnt % NUM_PER_INDEX == 0) {
                indexes.add(cursor);
            }
            
            cnt++;
            cursor = cursor.next;
        }
        return cnt;
    }
    
    public int getRandom() {
        final int randomIndex = random.nextInt(count); // [0, count)
        final int indexOfIndex = randomIndex / NUM_PER_INDEX;

        int cursor = indexOfIndex * NUM_PER_INDEX;
        ListNode node = indexes.get(indexOfIndex);

        while (cursor != randomIndex) {
            node = node.next;
            cursor++;
        }
        
        return node.val;
    }
}
